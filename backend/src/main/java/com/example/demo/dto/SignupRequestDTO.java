package com.example.demo.dto;

import lombok.Getter;

@Getter
public class SignupRequestDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String initial;
    private String color;
    private String password;
    private String email;
}
