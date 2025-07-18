package com.example.demo.dto;

import com.example.demo.model.UserModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String initial;
    private String color;
    private String password;
    private String email;

    public static LoginResponseDTO from(UserModel userEntity) {
        return LoginResponseDTO.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .initial(userEntity.getInitial())
                .color(userEntity.getColor())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .build();
    }
}
