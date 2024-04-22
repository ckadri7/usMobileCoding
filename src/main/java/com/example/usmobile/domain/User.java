package com.example.usmobile.domain;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    String Id;
    String firstName;
    String lastName;
    @Indexed(unique = true)
    String email;
    String password;
    @Nullable
    @Indexed(unique = true, sparse = true)
    String mdn;

    public User(String firstName, String lastName, String email, String password,String mdn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mdn=mdn;
    }
}
