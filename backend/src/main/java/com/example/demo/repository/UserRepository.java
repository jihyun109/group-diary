package com.example.demo.repository;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.LogInRequestDTO;
import com.example.demo.dto.UserUpdateRequestDTO;

public interface UserRepository {
    LoginResponseDTO login(LogInRequestDTO logInRequestDTO);
    void updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
}
