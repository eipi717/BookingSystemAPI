package com.example.BookingSystem.repository;

import com.example.BookingSystem.entity.BSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BSUser, Long> {
    BSUser findByUsername(String username);
}
