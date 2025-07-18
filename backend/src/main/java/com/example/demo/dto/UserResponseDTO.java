package com.example.demo.dto;

import com.example.demo.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String color;
    private String password;
    private String email;

    public static UserResponseDTO from (UserModel userModel) {
        return UserResponseDTO.builder()
                .id(userModel.getId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .color(userModel.getColor())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .build();
    }
}
