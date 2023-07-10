package com.example.BookingSystem.controller;

import com.example.BookingSystem.DTO.BookingDTO;
import com.example.BookingSystem.DTO.ListResponseDTO;
import com.example.BookingSystem.converter.BookingDTOConverter;
import com.example.BookingSystem.entity.BSUser;
import com.example.BookingSystem.entity.Booking;
import com.example.BookingSystem.service.BookingService;
import com.example.BookingSystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getBookingList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBookingList(
            @RequestParam(value = "filter_user", required = false, defaultValue = "") String filterBookingUser,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "orderBy", required = false, defaultValue = "") String orderBy,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction
    ) {
        ListResponseDTO listResponseDTO = new ListResponseDTO();
        List<Booking> bookingList = bookingService.getByCriteria(filterBookingUser, pageNumber, pageSize, orderBy, direction);
        List<BookingDTO> bookingDTOList = new ArrayList<>();

        for(Booking booking : bookingList) { bookingDTOList.add(BookingDTOConverter.convert(booking)); }

        long count = bookingService.countByCriteria(filterBookingUser);
        int pageCount = (int) Math.ceil((double) count / pageSize);

        listResponseDTO.setData(bookingDTOList);
        listResponseDTO.setTotalResponse(count);
        listResponseDTO.setNumberOfPages(pageCount);

        return new ResponseEntity<>(listResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBookingById/{bookingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        BookingDTO bookingDTO = BookingDTOConverter.convert(booking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/createBooking", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBooking/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "updateBooking/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        Booking booking = bookingService.getBookingById(id);

        BSUser bsUser = userService.getUserByUsername(bookingDTO.getBookingUser());
        booking.setUser(bsUser);
        booking.setPhone(bookingDTO.getPhoneNumber());
        booking.setUpdateTs(new Date().getTime());
        bookingService.updateBooking(id, booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @RequestMapping(value = "getRecommendedLesson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRecommendedLesson(@RequestParam(value = "user_name") String userName) {
        String recommendedList = bookingService.getRecommendedLessons(userName);

        return new ResponseEntity<>(recommendedList, HttpStatus.OK);
    }
}
