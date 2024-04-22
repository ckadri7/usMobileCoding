package com.example.usmobile.service.serviceImpl;

import com.example.usmobile.domain.User;
import com.example.usmobile.exception.EntityAlreadyExists;
import com.example.usmobile.repository.UserRepository;
import com.example.usmobile.request.UserRequest;
import com.example.usmobile.response.UserResponse;
import com.example.usmobile.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest)   {
        User user = userRequestToUser(userRequest);
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){throw new EntityAlreadyExists("User with the email :"+user.getEmail()+" already exists");}
        if( userRepository.findUserByMdn(user.getMdn()).isPresent()){throw new EntityAlreadyExists("User with the mdn :"+user.getMdn()+" already exists");}
        userRepository.insert(user);

        UserResponse userResponse = userToUserResponse(user);
        return userResponse;
}
    @Override
    public UserResponse updateUser(UserRequest userRequest){
        // log
        User user = userRequestToUser(userRequest);
        userRepository.save(user);
        UserResponse userResponse = userToUserResponse(user);
        return userResponse;
    }

    @Override
    public void transferMdn(String sourceUserId, String targetUserId) {
        //fetching user a and user b By Id
        User sourceUser = userRepository.findById(sourceUserId).orElseThrow(() -> new RuntimeException("Source user not found"));
        User targetUser = userRepository.findById(targetUserId).orElseThrow(() -> new RuntimeException("Target user not found"));

        // set the mdn in source to null to avoid duplication of the mdn
        String mdnToTransfer = sourceUser.getMdn();
        sourceUser.setMdn(null);
        userRepository.save(sourceUser);

        // transfer the mdn to user B
        targetUser.setMdn(mdnToTransfer);
        userRepository.save(targetUser);
    }


    private static User userRequestToUser(UserRequest userRequest){
        User user = new User(userRequest.getFirstName(),
                userRequest.getLastName(), userRequest.getEmail(), userRequest.getPassword(),userRequest.getMdn());
        return user;
    }

    private static UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse(user.getId(), user.getFirstName(),user.getLastName(), user.getEmail(),  user.getMdn());
        return userResponse;
    }
}
