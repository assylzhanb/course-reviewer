package com.group1.coursereview.controller;


import com.group1.coursereview.model.UserModel;
import com.group1.coursereview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

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

}
