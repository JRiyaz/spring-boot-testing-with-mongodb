package com.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotEmpty(message = "Name field cannot be empty")
    private String name;

    @Email(message = "Invalid Email ID")
    @NotEmpty(message = "Email field cannot be empty")
    private String email;

    @NotEmpty(message = "Gender field cannot be empty")
    private String gender;

    public User(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }
}
