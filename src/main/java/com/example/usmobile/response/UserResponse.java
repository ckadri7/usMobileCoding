package com.example.usmobile.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String email;
    String mdn;
}
