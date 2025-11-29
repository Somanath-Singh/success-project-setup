package com.aashdit.prod.heads.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;


/**
 * Implementation of the detector for Adobe PDF document.
 */
public class PdfDocumentDetectorImpl {

    private static final List<Pattern> DISALLOWED_PATTERNS = Arrays.asList(
            Pattern.compile("<script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("eval\\((.*)\\)", Pattern.CASE_INSENSITIVE),
            // Pattern.compile("<.*?>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("/JavaScript", Pattern.CASE_INSENSITIVE),
            Pattern.compile("style=[\"'].*?\"", Pattern.CASE_INSENSITIVE),
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE),
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            Pattern.compile("on\\w+\\s*=\\s*\"[^\"]*\"", Pattern.CASE_INSENSITIVE),
            Pattern.compile("iframe", Pattern.CASE_INSENSITIVE),
            Pattern.compile("document\\.cookie", Pattern.CASE_INSENSITIVE),
            Pattern.compile("document\\.write", Pattern.CASE_INSENSITIVE),
            Pattern.compile("window\\.location", Pattern.CASE_INSENSITIVE)
    );

    /**
     * LOGGER
     */
    private static final Logger log = LoggerFactory.getLogger(PdfDocumentDetectorImpl.class);
    
    public boolean isSafe(InputStream is) {
        boolean safeState = false;

        try {
            // Copy InputStream to ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            byte[] byteArray = baos.toByteArray();

            // Create a new InputStream from the byte array
            ByteArrayInputStream pdfStream = new ByteArrayInputStream(byteArray);

            if (pdfStream != null) {
                PdfReader reader = new PdfReader(pdfStream);
                String jsCode = reader.getJavaScript();
                if (jsCode == null) {
                    safeState = true;

                    PdfDictionary root = reader.getCatalog();
                    PdfDictionary names = root.getAsDict(PdfName.NAMES);
                    PdfArray namesArray = null;
                    try {
                        if (names != null && names.size() > 0) {
                            PdfDictionary embeddedFiles = names.getAsDict(PdfName.EMBEDDEDFILES);
                            namesArray = embeddedFiles.getAsArray(PdfName.NAMES);

                            safeState = safeState && ((namesArray == null) || namesArray.isEmpty());

                            if (safeState) {
                                safeState = checkPdfContent(new ByteArrayInputStream(byteArray));
                            }
                        } else {
                            safeState = checkPdfContent(new ByteArrayInputStream(byteArray));
                        }
                    } catch (Exception ex) {
                        safeState = false;
                        log.warn("embeddedFiles in PDF File threw an exception -> " + ex.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            safeState = false;
            log.warn("Error during PDF file analysis!", e);
        }
        log.debug("isSafeFile=======================" + safeState);
        return safeState;
    }

    private boolean checkPdfContent(InputStream file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (Pattern pattern : DISALLOWED_PATTERNS) {
                    if (pattern.matcher(line).find()) {
                        log.error("Disallowed content found in file: {0}");
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error reading file content: {0}", e);
            return false;
        }
        return true;
    }
}