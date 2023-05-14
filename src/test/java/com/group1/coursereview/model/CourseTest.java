package com.group1.coursereview.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CourseTest {
    @Test
    void constructor_CreatesCourseWithCorrectValues() {
        String school = "University";
        String courseCode = "CSCI101";
        String courseTitle = "Introduction to Computer Science";
        String professorName = "John Doe";
        String program = "Computer Science";
        String prerequisite = "None";

        Course course = new Course(school, courseCode, courseTitle, professorName, program, prerequisite);

        Assertions.assertEquals(school, course.getSchool());
        Assertions.assertEquals(courseCode, course.getCourseCode());
        Assertions.assertEquals(courseTitle, course.getCourseTitle());
        Assertions.assertEquals(professorName, course.getProfessorName());
        Assertions.assertEquals(program, course.getProgram());
        Assertions.assertEquals(prerequisite, course.getPrerequisite());
    }


    @Test
    void getId_ReturnsCorrectValue() {
        String id = "course123";
        Course course = new Course();
        course.setId(id);

        String actualId = course.getId();

        Assertions.assertEquals(id, actualId);
    }

    @Test
    void getSchool_ReturnsCorrectValue() {
        String school = "Example University";
        Course course = new Course();
        course.setSchool(school);

        String actualSchool = course.getSchool();

        Assertions.assertEquals(school, actualSchool);
    }

    @Test
    void getCourseCode_ReturnsCorrectValue() {
        String courseCode = "CS101";
        Course course = new Course();
        course.setCourseCode(courseCode);

        String actualCourseCode = course.getCourseCode();

        Assertions.assertEquals(courseCode, actualCourseCode);
    }

    @Test
    void getCourseTitle_ReturnsCorrectValue() {
        String courseTitle = "Introduction to Computer Science";
        Course course = new Course();
        course.setCourseTitle(courseTitle);

        String actualCourseTitle = course.getCourseTitle();

        Assertions.assertEquals(courseTitle, actualCourseTitle);
    }

    @Test
    void getProfessorName_ReturnsCorrectValue() {
        String professorName = "John Doe";
        Course course = new Course();
        course.setProfessorName(professorName);

        String actualProfessorName = course.getProfessorName();

        Assertions.assertEquals(professorName, actualProfessorName);
    }

    @Test
    void getProgram_ReturnsCorrectValue() {
        String program = "Computer Science";
        Course course = new Course();
        course.setProgram(program);

        String actualProgram = course.getProgram();

        Assertions.assertEquals(program, actualProgram);
    }

    @Test
    void getPrerequisite_ReturnsCorrectValue() {
        String prerequisite = "None";
        Course course = new Course();
        course.setPrerequisite(prerequisite);

        String actualPrerequisite = course.getPrerequisite();

        Assertions.assertEquals(prerequisite, actualPrerequisite);
    }
}
