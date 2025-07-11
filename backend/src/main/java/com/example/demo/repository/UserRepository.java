package com.example.demo.repository;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.LogInRequestDTO;

public interface UserRepository {
    LoginResponseDTO login(LogInRequestDTO logInRequestDTO);
}
