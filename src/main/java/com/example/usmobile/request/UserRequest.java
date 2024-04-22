package com.example.usmobile.request;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    String mdn;
}
