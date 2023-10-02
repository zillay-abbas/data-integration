package com.example.dataintegration.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(String dateStr) {
        try {
            return (Date) dateFormat.parse(dateStr);
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null; // or throw an exception
        }
    }
}
