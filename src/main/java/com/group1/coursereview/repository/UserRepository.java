package com.group1.coursereview.repository;

import com.group1.coursereview.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByStudentId(String studentId);
}
