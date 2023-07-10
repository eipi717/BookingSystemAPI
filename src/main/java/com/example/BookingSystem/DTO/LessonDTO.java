package com.example.BookingSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDTO {

    private Long lessonId;

    private String lessonName;

    private String lessonDescription;

    private String lessonStartTime;

    private String lessonEndTime;

    private Long lessonDuration;

    private Double lessonPrice;

    private String createTs;

    private String updateTs;
}
