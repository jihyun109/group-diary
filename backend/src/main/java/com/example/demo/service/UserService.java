package com.example.demo.service;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.LogInRequestDTO;
import com.example.demo.dto.UserUpdateRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.user.UserModel;

import java.util.List;

public interface UserService {
    UserResponseDTO getUser(long userId);
    List<UserModel> getUsers();
    void insertUser(UserModel user);
    void updateUser(int userId, UserUpdateRequestDTO user);
    void deleteUser(int userId);
    List<UserModel> userEmailSearchModel(String searchWord);
    LoginResponseDTO logIn(LogInRequestDTO user);
}
