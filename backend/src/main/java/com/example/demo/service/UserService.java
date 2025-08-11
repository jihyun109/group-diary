package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.UserModel;

import java.util.List;

public interface UserService {
    UserResponseDTO getUser(long userId);

    void insertUser(SignupRequestDTO user);

    void updateUser(long userId, UserUpdateRequestDTO user);

    void deleteUser(long userId);

    List<UserModel> userEmailSearchModel(String searchWord);

    LoginResponseDTO logIn(LogInRequestDTO user);

    Long findUserIdByEmail(String email);
}
