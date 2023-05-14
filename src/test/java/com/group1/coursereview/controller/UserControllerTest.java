package com.group1.coursereview.controller;

import com.group1.coursereview.model.SessionModel;
import com.group1.coursereview.model.UserModel;
import com.group1.coursereview.repository.SessionRepository;
import com.group1.coursereview.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }
    private UserModel createUser(String email, String password, String name) {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    @Test
    void login_ExistingUser_InvalidCredentials_Conflict() {
        UserModel existingUser = createUser("test@example.com", "123456", "John Doe");
        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(existingUser);

        UserModel userWithInvalidCredentials = createUser("test@example.com", "wrongpassword", "John Doe");
        ResponseEntity<String> response = userController.login(userWithInvalidCredentials);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Incorrect password", response.getBody());
        verify(sessionRepository, never()).save(any(SessionModel.class));
    }

    @Test
    void login_NonexistentUser_NotFound() {
        UserModel nonExistentUser = createUser("nonexistent@example.com", "123456", "Non Existent");
        when(userRepository.findByEmail(nonExistentUser.getEmail())).thenReturn(null);

        ResponseEntity<String> response = userController.login(nonExistentUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
        verify(sessionRepository, never()).save(any(SessionModel.class));
    }

    @Test
    void login_UserAlreadyLoggedIn_Conflict() {
        UserModel existingUser = createUser("test@example.com", "123456", "John Doe");
        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(existingUser);
        when(sessionRepository.findByUserId(existingUser.getId())).thenReturn(new SessionModel());

        ResponseEntity<String> response = userController.login(existingUser);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("User is already logged in", response.getBody());
        verify(sessionRepository, never()).save(any(SessionModel.class));
    }

    @Test
    void logout_ValidSession_LogsOutSuccessfully() {
        ResponseEntity<String> response = userController.logout();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Logged out successfully", response.getBody());
        verify(sessionRepository, times(1)).deleteAll();
    }

    @Test
    void signup_NewUser_SuccessfulSignup() {
        UserModel user = createUser("test@example.com", "12345678", "John Doe");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.findByStudentId(user.getStudentId())).thenReturn(null);

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Signup successful", response.getBody());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void signup_EmailAlreadyExists_Conflict() {
        UserModel user = createUser("test@example.com", "123456", "John Doe");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email already exists", response.getBody());
        verify(userRepository, never()).save(user);
    }
    @Test
    void signup_EmailIsNull_ReturnsBadRequest() {
        UserModel user = createUser(null, "123456", "John Doe");

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is required", response.getBody());
    }

    @Test
    void signup_EmailIsEmpty_ReturnsBadRequest() {
        UserModel user = createUser("", "123456", "John Doe");

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is required", response.getBody());
    }

    @Test
    void signup_PasswordIsNull_ReturnsBadRequest() {
        UserModel user = createUser("test@example.com", null, "John Doe");

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password is required", response.getBody());
    }

    @Test
    void signup_PasswordIsEmpty_ReturnsBadRequest() {
        UserModel user = createUser("test@example.com", "", "John Doe");

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password is required", response.getBody());
    }
    @Test
    void signup_StudentIdAlreadyExists_ReturnsConflict() {
        UserModel user = createUser("test@example.com", "123456", "John Doe");
        UserModel existingUser = createUser("another@example.com", "789012", "Jane Smith");
        user.setStudentId("12345");  // Set a non-null student ID
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.findByStudentId(user.getStudentId())).thenReturn(existingUser); // Make findByStudentId return an existing user

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Student ID already exists", response.getBody());
    }

    @Test
    void signup_MissingEmail_ReturnsBadRequest() {
        UserModel user = createUser(null, "123456", "John Doe");

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is required", response.getBody());
    }

    @Test
    void signup_MissingPassword_ReturnsBadRequest() {
        UserModel user = createUser("test@example.com", null, "John Doe");

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password is required", response.getBody());
    }
    @Test
    void signup_ValidUserWithStudentId_ReturnsCreated() {
        UserModel user = createUser("test@example.com", "12345678", "John Doe");
        user.setStudentId("123");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.findByStudentId(user.getStudentId())).thenReturn(null);

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Signup successful", response.getBody());
    }
    @Test
    void login_MissingEmail_ReturnsBadRequest() {
        UserModel user = createUser(null, "123456", "John Doe");

        ResponseEntity<String> response = userController.login(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email is required", response.getBody());
    }

    @Test
    void login_MissingPassword_ReturnsBadRequest() {
        UserModel user = createUser("test@example.com", null, "John Doe");

        ResponseEntity<String> response = userController.login(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password is required", response.getBody());
    }

    @Test
    void login_ValidUser_ReturnsAccepted() {
        UserModel user = createUser("test@example.com", "123456", "John Doe");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        ResponseEntity<String> response = userController.login(user);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }





    @Test
    void signup_SaveUserException_ReturnsInternalServerError() {
        UserModel user = createUser("test@example.com", "12345678", "John Doe");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.findByStudentId(user.getStudentId())).thenReturn(null);
        doThrow(new RuntimeException("Failed to save user")).when(userRepository).save(user);

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to save user", response.getBody());
    }



    @Test
    void signup_NameIsNull_ReturnsBadRequest() {
        UserModel user = createUser("test@example.com", "123456", null);

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name is required", response.getBody());
    }

    @Test
    void signup_NameIsEmpty_ReturnsBadRequest() {
        UserModel user = createUser("test@example.com", "123456", "");

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name is required", response.getBody());
    }


    @Test
    void signup_StudentIdAlreadyExists_Conflict() {
        UserModel user = createUser("test@example.com", "12345678", "John Doe");
        UserModel existingUser = createUser("existing@example.com", "789012", "Jane Smith");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepository.findByStudentId(user.getStudentId())).thenReturn(existingUser);
        doThrow(DataIntegrityViolationException.class).when(userRepository).save(user); // Mock the save method to throw an exception

        ResponseEntity<String> response = userController.signup(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Student ID already exists", response.getBody());
    }
    @Test
    void validatePassword_ShortPassword_ReturnsBadRequest() {
        UserModel user = new UserModel();
        user.setPassword("pass");
        ResponseEntity<String> response = validatePassword(user);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Password should be more than 8 characters", response.getBody());
    }

    private ResponseEntity<String> validatePassword(UserModel user) {
        if (user.getPassword().length() < 8) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password should be more than 8 characters");
        } else {
            return ResponseEntity.ok().build();
        }
    }


    @Test
    void login_ExistingUser_ValidCredentials_SuccessfulLogin() {
        UserModel existingUser = createUser("test@example.com", "123456", "John Doe");
        when(userRepository.findByEmail(existingUser.getEmail())).thenReturn(existingUser);
        when(sessionRepository.findByUserId(existingUser.getId())).thenReturn(null);
        when(sessionRepository.save(any(SessionModel.class))).thenReturn(null);

        ResponseEntity<String> response = userController.login(existingUser);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Logged in successfully", response.getBody());
        verify(sessionRepository, times(1)).save(any(SessionModel.class));
    }
}