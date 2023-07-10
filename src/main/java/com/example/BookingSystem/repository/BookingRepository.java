package com.example.BookingSystem.repository;

import com.example.BookingSystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{

    List<Booking> findByCriteria(String bookingUser, int page, int size, String orderBy, String direction);
    Long countByCriteria(String bookingUser);
}
