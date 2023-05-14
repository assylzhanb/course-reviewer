package com.group1.coursereview.controller;

import com.group1.coursereview.model.Course;
import com.group1.coursereview.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CourseControllerTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCoursesByName_ValidName_ReturnsCourses() {
        String courseName = "Introduction to Programming";
        List<Course> expectedCourses = createCoursesList(courseName, 3);
        when(courseRepository.findByCourseTitleIgnoreCase(courseName)).thenReturn(expectedCourses);

        ResponseEntity<List<Course>> response = courseController.getCoursesByName(courseName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCourses, response.getBody());
    }

    @Test
    void getCoursesByCode_ValidCode_ReturnsCourse() {
        String courseCode = "CSC101";
        Course expectedCourse = createCourse(courseCode, "Introduction to Programming");
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(expectedCourse);

        ResponseEntity<Course> response = courseController.getCoursesByCode(courseCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCourse, response.getBody());
    }

    private List<Course> createCoursesList(String courseName, int numCourses) {
        List<Course> courses = new ArrayList<>();
        for (int i = 1; i <= numCourses; i++) {
            Course course = new Course();
            course.setCourseCode("CSC" + i);
            course.setCourseTitle(courseName);
            courses.add(course);
        }
        return courses;
    }

    private Course createCourse(String courseCode, String courseName) {
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseTitle(courseName);
        return course;
    }
}
