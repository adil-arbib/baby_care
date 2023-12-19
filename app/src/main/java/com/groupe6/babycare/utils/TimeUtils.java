package com.groupe6.babycare.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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


    public static String formatFromSqlDateToRegular(String sqlDate) {
        try {
            OffsetDateTime offsetDateTime = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                offsetDateTime = OffsetDateTime.parse(sqlDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }

            DateTimeFormatter outputFormatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            }
            String outputDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                outputDate = offsetDateTime.format(outputFormatter);
            }

            return outputDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertToISOFormat(String inputDate) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(inputDate, inputFormatter);

            OffsetDateTime offsetDateTime = localDate.atStartOfDay().atOffset(ZoneOffset.UTC);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            String outputDate = offsetDateTime.format(outputFormatter);

            return outputDate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatSqlDatetime(String sqlDatetimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        LocalDateTime parsedDatetime = LocalDateTime.parse(sqlDatetimeStr, formatter);

        String formattedDatetime = parsedDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formattedDatetime;
    }

}
