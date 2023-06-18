package com.group1.coursereview.controller;

import com.group1.coursereview.model.SessionModel;
import com.group1.coursereview.model.UserModel;
import com.group1.coursereview.repository.SessionRepository;
import com.group1.coursereview.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserModel user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }
//        if (!user.getEmail().endsWith("@unist.ac.kr")){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Must be UNIST email");
//        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is required");
        }


        if (user.getName() == null || user.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is required");
        }

        UserModel existingByEmail = userRepository.findByEmail(user.getEmail());
        if (existingByEmail != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        if (user.getStudentId() != null) {
            UserModel existingByStudentId = userRepository.findByStudentId(user.getStudentId());
            if (existingByStudentId != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Student ID already exists");
            }
        }if(user.getPassword().length() < 8){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password should be more than 8 characters");
        }

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student ID already exists");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save user");
        }


        return ResponseEntity.status(HttpStatus.CREATED).body("Signup successful");
    }



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is required");
        }
        UserModel existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            if (user.getPassword().equals(existingUser.getPassword())) {
                SessionModel session = sessionRepository.findByUserId(existingUser.getId());
                if (session != null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already logged in");
                }
                String sessionId = generateSessionId();

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