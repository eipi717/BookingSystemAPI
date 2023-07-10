package com.example.BookingSystem.repository;

import com.example.BookingSystem.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Lesson findByLessonName(String lessonName);

    List<Lesson> findByCriteria(int page, int size);

    Long countByCriteria();
}
