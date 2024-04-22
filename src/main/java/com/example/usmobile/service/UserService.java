package com.example.usmobile.service;

import com.example.usmobile.exception.EntityAlreadyExists;
import com.example.usmobile.request.UserRequest;
import com.example.usmobile.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserResponse createUser(UserRequest user) throws EntityAlreadyExists;

    public UserResponse updateUser(UserRequest user);

    public void transferMdn(String sourceUserId, String transferredToUserId);
}
