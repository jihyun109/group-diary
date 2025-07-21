package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.UserModel;

import java.util.List;

public interface UserService {
    UserResponseDTO getUser(long userId);

    void insertUser(SignupRequestDTO user);

    void updateUser(int userId, UserUpdateRequestDTO user);

    void deleteUser(int userId);

    List<UserModel> userEmailSearchModel(String searchWord);

    LoginResponseDTO logIn(LogInRequestDTO user);
}
