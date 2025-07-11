package com.example.demo.repository;

import com.example.demo.dto.LogInRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.UserUpdateRequestDTO;
import com.example.demo.user.UserMapper;
import com.example.demo.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRepositoryImplMybatis implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public LoginResponseDTO login(LogInRequestDTO logInRequestDTO) {
        UserModel userModel = userMapper.logIn(logInRequestDTO);

        return LoginResponseDTO.from(userModel);
    }

    @Override
    public void updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        userMapper.updateUser(userUpdateRequestDTO);
    }

    @Override
    public List<UserModel> getUsers() {
        return userMapper.selectUsers();
    }
}
