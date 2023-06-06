package com.group1.coursereview.controller;

import com.group1.coursereview.model.Course;
import com.group1.coursereview.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/byName/{name}")
    public ResponseEntity<List<Course>> getCoursesByName(@PathVariable String name) {
        List<Course> courses = courseRepository.findByCourseTitleIgnoreCase(name);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/byCode/{code}")
    public ResponseEntity<Course> getCoursesByCode(@PathVariable String code) {
        Course courses = courseRepository.findByCourseCode(code);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
