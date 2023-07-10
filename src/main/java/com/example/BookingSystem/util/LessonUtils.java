package com.example.BookingSystem.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LessonUtils {
    public static long getDuration(String startTime, String endTime) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        long minutes = ChronoUnit.MINUTES.between(start, end);
        return minutes;
    }
}
