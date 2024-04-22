package com.example.usmobile.repository;

import com.example.usmobile.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByMdn(String mdn);
}
