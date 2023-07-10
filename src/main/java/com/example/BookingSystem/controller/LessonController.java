package com.example.BookingSystem.controller;

import com.example.BookingSystem.DTO.LessonDTO;
import com.example.BookingSystem.DTO.ListResponseDTO;
import com.example.BookingSystem.converter.LessonDTOConverter;
import com.example.BookingSystem.entity.Lesson;
import com.example.BookingSystem.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @RequestMapping(value = "/getLessonList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLessonList(
//            @RequestParam(value = "filter_lesson", required = false, defaultValue = "") String filterLessonName,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize
    ) {
        ListResponseDTO listResponseDTO = new ListResponseDTO();
        List<Lesson> lessonList = lessonService.getByCriteria(pageNumber, pageSize);
//        List<Lesson> lessonList = lessonService.getLessonList();
        List<LessonDTO> lessonDTOList = new ArrayList<>();

        for(Lesson lesson: lessonList) { lessonDTOList.add(LessonDTOConverter.convert(lesson)); }

        long count = lessonService.countByCriteria();
        int pageCount = (int) Math.ceil((double) count / pageSize);

        listResponseDTO.setTotalResponse(count);
        listResponseDTO.setNumberOfPages(pageCount);
        listResponseDTO.setData(lessonDTOList);

        return new ResponseEntity<>(listResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/getLessonById/{lessonId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLessonById(@PathVariable Long lessonId) {
        Lesson lesson = lessonService.getLessonById(lessonId);
        LessonDTO lessonDTO = LessonDTOConverter.convert(lesson);
        return new ResponseEntity<>(lessonDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/createLesson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createLesson(@RequestBody LessonDTO lessonDTO) {
        lessonService.createLesson(lessonDTO);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteLesson/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "updateLesson/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        Lesson lesson = lessonService.getLessonById(id);

        lesson.setLessonName(lessonDTO.getLessonName());
        lesson.setLessonDescription(lessonDTO.getLessonDescription());
        lesson.setStartTime(lessonDTO.getLessonStartTime());
        lesson.setEndTime(lessonDTO.getLessonEndTime());
        lesson.setLessonPrice(lessonDTO.getLessonPrice());
        lesson.setUpdateTs(new Date().getTime());

        lessonService.updateLesson(id, lesson);
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }
}
