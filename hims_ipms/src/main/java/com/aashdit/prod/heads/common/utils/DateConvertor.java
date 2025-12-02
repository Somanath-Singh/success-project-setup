package com.aashdit.prod.heads.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

    private static final String DEFAULT_PATTERN = "dd-MM-yyyy";
    private static final String DB_PATTERN = "yyyy-MM-dd";

    /**
     * Converts String ("dd-MM-yyyy") to java.util.Date
     */
    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
            sdf.setLenient(false);
            return sdf.parse(dateStr.trim());
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                "Invalid date format: " + dateStr + ". Expected: dd-MM-yyyy"
            );
        }
    }

    /**
     * Converts java.util.Date to String ("dd-MM-yyyy")
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        return sdf.format(date);
    }

    /**
     * Converts "dd-MM-yyyy" -> "yyyy-MM-dd"
     * Safely returns null if input invalid.
     */
    public static String formatForDb(String dateStr) {
        Date parsed = parseDate(dateStr); // uses your existing strict parser
        if (parsed == null) {
            return null;
        }
        return new SimpleDateFormat(DB_PATTERN).format(parsed);
    }

    /**
     * Converts "yyyy-MM-dd" -> "dd-MM-yyyy"
     * Useful when showing database dates in UI.
     */
    public static String formatForUi(String dbDateStr) {
        if (dbDateStr == null || dbDateStr.trim().isEmpty()) {
            return null;
        }

        try {
            Date parsed = new SimpleDateFormat(DB_PATTERN).parse(dbDateStr.trim());
            return new SimpleDateFormat(DEFAULT_PATTERN).format(parsed);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                "Invalid DB date format: " + dbDateStr + ". Expected: yyyy-MM-dd"
            );
        }
    }
    
}
