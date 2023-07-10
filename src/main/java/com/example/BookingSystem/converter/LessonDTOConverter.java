package com.example.BookingSystem.converter;

import com.example.BookingSystem.DTO.LessonDTO;
import com.example.BookingSystem.entity.Lesson;
import com.example.BookingSystem.helpers.DateTimeHelper;
import com.example.BookingSystem.util.LessonUtils;

public class LessonDTOConverter {

    public static LessonDTO convert(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();

        lessonDTO.setLessonId(lesson.getLessonId());
        lessonDTO.setLessonName(lesson.getLessonName());
        lessonDTO.setLessonDescription(lesson.getLessonDescription());
        lessonDTO.setLessonStartTime(lesson.getStartTime());
        lessonDTO.setLessonEndTime(lesson.getEndTime());
        lessonDTO.setLessonDuration(LessonUtils.getDuration(lesson.getStartTime(), lesson.getEndTime()));
        lessonDTO.setLessonPrice(lesson.getLessonPrice());
        lessonDTO.setCreateTs(DateTimeHelper.tsToDateString(lesson.getCreateTs()));
        lessonDTO.setUpdateTs(DateTimeHelper.tsToDateString(lesson.getUpdateTs()));
        return lessonDTO;
    }
}
