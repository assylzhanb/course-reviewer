package com.group1.coursereview.repository;


import com.group1.coursereview.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
    UserModel findByEmail(String email);
    UserModel findByStudentId(String studentId);
}
