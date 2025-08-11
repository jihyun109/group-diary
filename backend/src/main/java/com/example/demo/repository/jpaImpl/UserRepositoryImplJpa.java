package com.example.demo.repository.jpaImpl;

import com.example.demo.dto.*;
import com.example.demo.repository.inter.UserRepository;

public class UserRepositoryImplJpa implements UserRepository {
    @Override
    public LoginResponseDTO login(LogInRequestDTO logInRequestDTO) {
        return null;
    }

    @Override
    public void updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {

    }

    @Override
    public UserResponseDTO getUser(long userId) {
        return null;
    }

    @Override
    public void insertUser(SignupRequestDTO signupRequestDTO) {

    }

    @Override
    public Long findUserIdByEmail(String email) {
        return null;
    }
}
