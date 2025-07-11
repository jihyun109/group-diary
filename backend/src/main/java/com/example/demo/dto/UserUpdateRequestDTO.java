package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequestDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String initial;
    private String color;
    private String password;
    private String email;
}
