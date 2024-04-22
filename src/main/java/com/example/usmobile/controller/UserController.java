package com.example.usmobile.controller;

import com.example.usmobile.exception.EntityAlreadyExists;
import com.example.usmobile.request.UserRequest;
import com.example.usmobile.response.UserResponse;
import com.example.usmobile.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Api(tags = "User APIs", description = "Endpoints for managing User data")
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;

//    @ApiOperation("create a user")
    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user) throws EntityAlreadyExists {
        UserResponse createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

//    @ApiOperation("update a user")
    @PutMapping()
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest user){
        UserResponse updatedUser = userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

  //  @ApiOperation("Transfer an MDN from userSource to userTarget")
    @PostMapping("/mdn-transfer")
    public  void transferMdn(@RequestParam("sourceUserId") String sourceUserId,
                            @RequestParam("targetUserId") String targetUserId){
        userService.transferMdn(sourceUserId,targetUserId);
    }

}
