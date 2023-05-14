package com.group1.coursereview.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionModelTest {
    @Test
    void getSessionId_ReturnsCorrectValue() {
        String sessionId = "session123";
        String userId = "user456";
        SessionModel session = new SessionModel(sessionId, userId);

        String actualSessionId = session.getSessionId();

        Assertions.assertEquals(sessionId, actualSessionId);
    }

    @Test
    void setSessionId_SetsCorrectValue() {
        String sessionId = "session123";
        String userId = "user456";
        SessionModel session = new SessionModel();

        session.setSessionId(sessionId);

        Assertions.assertEquals(sessionId, session.getSessionId());
    }

    @Test
    void getUserId_ReturnsCorrectValue() {
        String sessionId = "session123";
        String userId = "user456";
        SessionModel session = new SessionModel(sessionId, userId);

        String actualUserId = session.getUserId();

        Assertions.assertEquals(userId, actualUserId);
    }

    @Test
    void setUserId_SetsCorrectValue() {
        String sessionId = "session123";
        String userId = "user456";
        SessionModel session = new SessionModel();

        session.setUserId(userId);

        Assertions.assertEquals(userId, session.getUserId());
    }
}
