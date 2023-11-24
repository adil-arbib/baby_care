package com.groupe6.babycare.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class TimeUtils {

    public static String getRemainingTime(String dateString) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O)
            return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime inputDateTime = LocalDateTime.parse(dateString, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(currentDateTime, inputDateTime);
        long days = ChronoUnit.DAYS.between(currentDateTime, inputDateTime);
        long hours = ChronoUnit.HOURS.between(currentDateTime, inputDateTime) % 24;
        long remainingMinutes = minutes % 60;
        StringBuilder result = new StringBuilder();
        if (days > 0) {
            result.append(days).append("d ");
        }
        if (hours > 0) {
            result.append(hours).append("h ");
        }
        result.append(remainingMinutes).append("min");
        return result.toString();
    }

    public String[] splitDate(String date) {
        return date.split(" ");
    }

}
