package com.example.BookingSystem.service;

import com.example.BookingSystem.entity.BSUser;
import com.example.BookingSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /***
     * Get user by username
     * @param username
     * @return BSUser
     */
    public BSUser getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        }
        catch (Exception e) {
            logger.error("HE: " + e.getMessage());
            return null;
        }
    }

    /***
     * Get user by id
     * @param userID
     * @return BSUser
     */
    public BSUser getUserById(Long userID) {
        try {
            return userRepository.getById(userID);
        }
        catch (Exception e) {
            logger.error("HE: " + e.getMessage());
            return null;
        }
    }
}
