package com.group1.coursereview.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfessorTest {

    @Test
    void getId_ReturnsCorrectValue() {
        String id = "prof123";
        Professor professor = new Professor();
        professor.setId(id);

        String actualId = professor.getId();

        Assertions.assertEquals(id, actualId);
    }
    @Test
    void constructor_CreatesProfessorWithCorrectValues() {
        String instId = "123456";
        String name = "John Doe";
        String contactInfo = "john.doe@example.com";
        String department = "Computer Science";
        String position = "Professor";
        String educationalBackground = "Ph.D. in Computer Science";

        Professor professor = new Professor(instId, name, contactInfo, department, position, educationalBackground);

        Assertions.assertEquals(instId, professor.getInstId());
        Assertions.assertEquals(name, professor.getName());
        Assertions.assertEquals(contactInfo, professor.getContactInfo());
        Assertions.assertEquals(department, professor.getDepartment());
        Assertions.assertEquals(position, professor.getPosition());
        Assertions.assertEquals(educationalBackground, professor.getEducationalBackground());
    }

    @Test
    void getInstId_ReturnsCorrectValue() {
        String instId = "inst123";
        Professor professor = new Professor();
        professor.setInstId(instId);

        String actualInstId = professor.getInstId();

        Assertions.assertEquals(instId, actualInstId);
    }

    @Test
    void getName_ReturnsCorrectValue() {
        String name = "John Doe";
        Professor professor = new Professor();
        professor.setName(name);

        String actualName = professor.getName();

        Assertions.assertEquals(name, actualName);
    }

    @Test
    void getContactInfo_ReturnsCorrectValue() {
        String contactInfo = "contact@example.com";
        Professor professor = new Professor();
        professor.setContactInfo(contactInfo);

        String actualContactInfo = professor.getContactInfo();

        Assertions.assertEquals(contactInfo, actualContactInfo);
    }

    @Test
    void getDepartment_ReturnsCorrectValue() {
        String department = "Computer Science";
        Professor professor = new Professor();
        professor.setDepartment(department);

        String actualDepartment = professor.getDepartment();

        Assertions.assertEquals(department, actualDepartment);
    }

    @Test
    void getPosition_ReturnsCorrectValue() {
        String position = "Professor";
        Professor professor = new Professor();
        professor.setPosition(position);

        String actualPosition = professor.getPosition();

        Assertions.assertEquals(position, actualPosition);
    }

    @Test
    void getEducationalBackground_ReturnsCorrectValue() {
        String educationalBackground = "PhD in Computer Science";
        Professor professor = new Professor();
        professor.setEducationalBackground(educationalBackground);

        String actualEducationalBackground = professor.getEducationalBackground();

        Assertions.assertEquals(educationalBackground, actualEducationalBackground);
    }
}
