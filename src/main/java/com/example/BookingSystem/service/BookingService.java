package com.example.BookingSystem.service;

import com.example.BookingSystem.DTO.BookingDTO;
import com.example.BookingSystem.entity.BSUser;
import com.example.BookingSystem.entity.Booking;
import com.example.BookingSystem.entity.Lesson;
import com.example.BookingSystem.repository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private final LessonService lessonService;

    @Value("${recommender.path}")
    private String recommendedScriptPath;

    @Value("${recommender.environment.name}")
    private String environmentName;

    public BookingService(BookingRepository bookingRepository, UserService userService, LessonService lessonService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.lessonService = lessonService;
    }

    /***
     * Get all bookings
     * @param bookingUser
     * @param page
     * @param size
     * @param orderBy
     * @param direction
     * @return List<Booking>
     */
    public List<Booking> getByCriteria(String bookingUser, int page, int size, String orderBy, String direction) {
        return bookingRepository.findByCriteria(bookingUser, page, size, orderBy, direction);
    }

    /***
     * Count all bookings
     * @param bookingUser
     * @return long
     */
    public long countByCriteria(String bookingUser) {
        long count = bookingRepository.countByCriteria(bookingUser);
        logger.info(String.format("%s booking returned by countByCriteria", count));
        return count;
    }

    /***
     * Get booking by id
     * @param id
     * @return Booking
     */
    public Booking getBookingById(Long id) {
        return bookingRepository.getById(id);
    }

    /***
     * Get booking by user
     * @param bookingDTO
     */
    public void createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        BSUser bsUser = userService.getUserByUsername(bookingDTO.getBookingUser());
        Lesson lesson = lessonService.getLessonByName(bookingDTO.getLessonName());

        if (lesson == null || bsUser == null) {
            return;
        }

        booking.setUser(bsUser);
        booking.setPhone(bookingDTO.getPhoneNumber());
        booking.setLesson(lesson);
        booking.setCreateTs(new Date().getTime());
        booking.setUpdateTs(new Date().getTime());
        bookingRepository.save(booking);
        logger.info("Booking created");
    }

    /***
     * Update booking
     * @param bookingId
     * @param booking
     */
    public void updateBooking(Long bookingId, Booking booking) {
        Booking old_booking = bookingRepository.getById(bookingId);

        old_booking.setUser(booking.getUser());
        old_booking.setPhone(booking.getPhone());

        old_booking.setUpdateTs(new Date().getTime());
        bookingRepository.save(booking);
        logger.info(String.format("Booking with id: %s update", bookingId));
    }

    /***
     * Delete booking
     * @param bookingId
     */
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
        logger.info(String.format("Booking with id: %s deleted", bookingId));
    }

    /***
     * Get recommended lessons
     * @param userName
     * @return String
     */
    public String getRecommendedLessons(String userName) {
        BSUser bsUser = userService.getUserByUsername(userName);
        StringBuilder recommendedLesson = new StringBuilder();
        try {
            logger.info(String.format("Running python script with path: %s", recommendedScriptPath));
            ProcessBuilder processBuilder = new ProcessBuilder("conda", "run", "-n", environmentName, "python", recommendedScriptPath, bsUser.getUsername());

            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                recommendedLesson.append(reader.readLine());
            }

        } catch (IOException e) {
            logger.error("Python Error: " + e.getMessage());
        }

        return recommendedLesson.toString();
    }
}
