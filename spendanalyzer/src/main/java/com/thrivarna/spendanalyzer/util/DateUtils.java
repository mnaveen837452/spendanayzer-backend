package com.thrivarna.spendanalyzer.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static String DATE_FORMAT = "dd-MM-yyyy";

    private static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static LocalDate stringToLocalDate(String date, String reqFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(reqFormatter);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate clientStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }

    public static String localDateToString(LocalDate localDate, String reqformatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(reqformatter);
        return localDate.format(formatter);
    }
}
