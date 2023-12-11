package com.test.application.user.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public final class Utils {

    private Utils() {

    }

    public static String getNewUuid() {
        return String.valueOf(UUID.randomUUID());
    }

    public static String convertDateToFormattedString(Date date, String format) {
        return date == null ? null : new SimpleDateFormat(format).format(date);
    }

    public static Boolean isInvalidString(String regex, String text) {
        return !Pattern.matches(regex, text);
    }

}
