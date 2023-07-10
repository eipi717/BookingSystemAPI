package com.example.BookingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lessons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Lesson {

    @Column(name = "LESSON_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @Column(name = "LESSON_name")
    private String lessonName;

    @Column(name = "LESSON_description")
    private String lessonDescription;

    @Column(name = "LESSON_start_time")
    private String startTime;

    @Column(name = "LESSON_end_time")
    private String endTime;

    @Column(name = "LESSON_price")
    private Double lessonPrice;

    @Column(name = "LESSON_createts")
    private Long createTs;

    @Column(name = "LESSON_updatets")
    private Long updateTs;
}
