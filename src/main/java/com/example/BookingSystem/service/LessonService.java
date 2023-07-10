package com.example.BookingSystem.service;

import com.example.BookingSystem.DTO.LessonDTO;
import com.example.BookingSystem.entity.Lesson;
import com.example.BookingSystem.repository.LessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    private final Logger logger = LoggerFactory.getLogger(LessonService.class);

    private final UserService userService;

    public LessonService(LessonRepository lessonRepository, UserService userService) {
        this.lessonRepository = lessonRepository;
        this.userService = userService;
    }

    /***
     * Get all lessons
     * @return List<Lesson>
     */
    public List<Lesson> getLessonList() {
        return lessonRepository.findAll();
    }

    /***
     * Get all lessons by criteria
     * @param page
     * @param size
     * @return List<Lesson>
     */
    public List<Lesson> getByCriteria(int page, int size) {
        return lessonRepository.findByCriteria(page, size);
    }

    /***
     * Count all lessons by criteria
     * @return count
     */
    public long countByCriteria() {
        long count = lessonRepository.countByCriteria();
        logger.info(String.format("%s booking returned by countByCriteria", count));
        return count;
    }

    /***
     * Get lesson by id
     * @param lessonDTO
     */
    public void createLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();

        lesson.setLessonName(lessonDTO.getLessonName());
        lesson.setStartTime(lessonDTO.getLessonStartTime());
        lesson.setEndTime(lessonDTO.getLessonEndTime());
        lesson.setLessonDescription(lessonDTO.getLessonDescription());
        lesson.setLessonPrice(lessonDTO.getLessonPrice());
        lesson.setCreateTs(new Date().getTime());
        lesson.setUpdateTs(new Date().getTime());
        lessonRepository.save(lesson);
        logger.info(String.format("Lesson %s created", lesson.getLessonName()));
    }

    /***
     * Update lesson by id
     * @param id
     * @param lesson
     */
    public void updateLesson(Long id, Lesson lesson) {
        Lesson oldLesson = lessonRepository.getById(id);

        oldLesson.setLessonName(lesson.getLessonName());
        oldLesson.setLessonDescription(lesson.getLessonDescription());
        oldLesson.setLessonPrice(lesson.getLessonPrice());
        oldLesson.setUpdateTs(new Date().getTime());
        lessonRepository.save(oldLesson);
        logger.info(String.format("Lesson %s updated", oldLesson.getLessonName()));
    }

    /***
     * Delete lesson by id
     * @param lessonId
     */
    public void deleteLesson(Long lessonId) {

        lessonRepository.deleteById(lessonId);
        logger.info(String.format("Lesson %s deleted", lessonId));
    }

    /***
     * Get lesson by id
     * @param lessonId
     * @return Lesson
     */
    public Lesson getLessonById(Long lessonId) { return lessonRepository.getById(lessonId); }

    /***
     * Get lesson by name
     * @param lessonName
     * @return Lesson
     */
    public Lesson getLessonByName(String lessonName) {
        return lessonRepository.findByLessonName(lessonName);
    }
}
