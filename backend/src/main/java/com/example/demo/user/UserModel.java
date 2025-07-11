package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    int id;
    String firstName;
    String lastName;
    String initial;
    String color;
    String password;
    String email;

    public UserModel() {
    }

    public UserModel(int id, String firstName, String lastName, String initial, String color, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initial = initial;
        this.color = color;
        this.password = password;
        this.email = email;
    }
    
}
