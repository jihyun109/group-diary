package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {
    private int id;
    private String firstName;
    private String lastName;
    private String initial;
    private String color;
    private String password;
    private String email;
}
