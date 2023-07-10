package com.example.BookingSystem.helpers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    public static String tsToDateString(Long ts) {
        Timestamp timestamp = new Timestamp(ts);
        LocalDateTime localDateTime = timestamp.toLocalDateTime().atZone(ZoneId.of("GMT+8")).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
