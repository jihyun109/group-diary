package com.example.demo.repository;

import com.example.demo.dto.*;
import com.example.demo.user.UserModel;

import java.util.List;

public interface UserRepository {
    LoginResponseDTO login(LogInRequestDTO logInRequestDTO);
    void updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    List<UserModel> getUsers();
    UserResponseDTO getUser(long userId);
    void insertUser(SignupRequestDTO signupRequestDTO);
}
