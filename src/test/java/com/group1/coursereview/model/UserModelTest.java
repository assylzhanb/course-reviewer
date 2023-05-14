package com.group1.coursereview.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserModelTest {

    @Test
    void getEmail_ReturnsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel(email, name, studentId, password);

        String actualEmail = user.getEmail();

        Assertions.assertEquals(email, actualEmail);
    }

    @Test
    void setEmail_SetsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel();

        user.setEmail(email);

        Assertions.assertEquals(email, user.getEmail());
    }

    @Test
    void getName_ReturnsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel(email, name, studentId, password);

        String actualName = user.getName();

        Assertions.assertEquals(name, actualName);
    }

    @Test
    void setName_SetsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel();

        user.setName(name);

        Assertions.assertEquals(name, user.getName());
    }

    @Test
    void getStudentId_ReturnsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel(email, name, studentId, password);

        String actualStudentId = user.getStudentId();

        Assertions.assertEquals(studentId, actualStudentId);
    }

    @Test
    void setStudentId_SetsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel();

        user.setStudentId(studentId);

        Assertions.assertEquals(studentId, user.getStudentId());
    }

    @Test
    void getPassword_ReturnsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel(email, name, studentId, password);

        String actualPassword = user.getPassword();

        Assertions.assertEquals(password, actualPassword);
    }
    @Test
    void setId_SetsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel();

        String id = "user123";
        user.setId(id);

        Assertions.assertEquals(id, user.getId());
    }

    @Test
    void getId_ReturnsCorrectValue() {
        String email = "test@example.com";
        String name = "John Doe";
        String studentId = "123456";
        String password = "password123";
        UserModel user = new UserModel(email, name, studentId, password);

        String actualId = user.getId();

        Assertions.assertNull(actualId);
    }
}