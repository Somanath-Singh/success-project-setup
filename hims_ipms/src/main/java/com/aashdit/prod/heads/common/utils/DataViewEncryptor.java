package com.aashdit.prod.heads.common.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * Encrypts a specific query param inside <a href> links in
 * GenericDataViewDto.dataValueList.
 *
 * Provides safe encryption with logging.
 * 
 * @author Somanath Singh
 * @since 18/08/2025
 */
@Slf4j
public class DataViewEncryptor {

    private DataViewEncryptor() {
        // utility class, prevent instantiation
    }

    /**
     * Encrypts specific query params inside <a href> tags.
     *
     * @param dataValueList  the raw data list fetched from DB (rows of strings)
     * @param paramToEncrypt the query param name that should be encrypted (e.g.
     *                       "workOrderId")
     * @return updated dataValueList with encrypted IDs in hrefs
     */
    public static List<List<String>> encryptLinks(List<List<String>> dataValueList, String paramToEncrypt) {
        if (dataValueList == null || dataValueList.isEmpty() || paramToEncrypt == null || paramToEncrypt.isBlank()) {
            log.debug("No data or param provided for encryption. Returning original list.");
            return dataValueList;
        }

        for (List<String> row : dataValueList) {
            for (int i = 0; i < row.size(); i++) {
                String cell = row.get(i);

                if (cell != null && cell.contains("<a href")) {
                    try {
                        String updatedLink = encryptParam(cell, paramToEncrypt);
                        row.set(i, updatedLink);
                        log.debug("Successfully encrypted param '{}' in link: {}", paramToEncrypt, updatedLink);
                    } catch (Exception e) {
                        log.error("Failed to encrypt param '{}' in link: {}. Error: {}", paramToEncrypt, cell, e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataValueList;
    }

    /**
     * Finds and encrypts a given param in an href string.
     *
     * @param link      the full <a href> HTML
     * @param paramName the query param to encrypt
     * @return updated link with encrypted param if found, otherwise original
     */
    private static String encryptParam(String link, String paramName) {
        Pattern pattern = Pattern.compile(paramName + "=(\\d+)");
        Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            String rawId = matcher.group(1);
            String encryptedId = EncryptionUtilGCM.encrypt(rawId); 
            return link.replace(paramName + "=" + rawId, paramName + "=" + encryptedId);
        }
        return link;
    }
}
