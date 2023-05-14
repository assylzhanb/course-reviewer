package com.group1.coursereview.repository;

import com.group1.coursereview.model.SessionModel;
import com.group1.coursereview.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<SessionModel, String> {

    SessionModel findBySessionId(String sessionId);
    SessionModel findByUserId(String userId);

    void deleteBySessionId(String sessionId);
}
