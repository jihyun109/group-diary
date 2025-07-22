package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserModel {
    private long id;
    private String firstName;
    private String lastName;
    private String initial;
    private String color;
    private String password;
    private String email;
}
