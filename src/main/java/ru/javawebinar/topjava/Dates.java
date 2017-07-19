package ru.javawebinar.topjava;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 This is the util class with utility method for LocalDateTime converting to needed format
 @return returns the required data format
 */

public final class Dates {
    private Dates() {}

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}