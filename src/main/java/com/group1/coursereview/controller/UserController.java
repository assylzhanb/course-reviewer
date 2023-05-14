package com.group1.coursereview.controller;

import com.group1.coursereview.model.SessionModel;
import com.group1.coursereview.model.UserModel;
import com.group1.coursereview.repository.SessionRepository;
import com.group1.coursereview.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserModel user) {
        // emailCHECK
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // idCHECK
        if (userRepository.findByStudentId(user.getStudentId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student ID already exists");
        }

        // now will be hashing pass somehow
        try {
            userRepository.save(user);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save user");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel user) {
        UserModel existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            if (user.getPassword().equals(existingUser.getPassword())) {
                // Check if session already exists
                SessionModel session = sessionRepository.findByUserId(existingUser.getId());
                if (session != null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already logged in");
                }

                // Generate a new session ID
                String sessionId = generateSessionId();

                // Create a new session
                SessionModel newSession = new SessionModel(sessionId, existingUser.getId());
                sessionRepository.save(newSession);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logged in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect password");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    private String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Remove the session
        sessionRepository.deleteAll();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logged out successfully");
    }
}