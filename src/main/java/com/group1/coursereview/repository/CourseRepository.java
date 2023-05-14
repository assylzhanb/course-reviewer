package com.group1.coursereview.repository;

import com.group1.coursereview.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByCourseTitleIgnoreCase(String name);
    Course findByCourseCode(String courseCode);
}
