package com.example.demo.repository.inter;

import com.example.demo.dto.*;

public interface UserRepository {
    LoginResponseDTO login(LogInRequestDTO logInRequestDTO);

    void updateUser(UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO getUser(long userId);

    void insertUser(SignupRequestDTO signupRequestDTO);
}
