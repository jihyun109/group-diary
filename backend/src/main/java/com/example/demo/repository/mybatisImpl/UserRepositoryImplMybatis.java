package com.example.demo.repository.mybatisImpl;

import com.example.demo.dto.*;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.repository.inter.UserRepository;
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

    @Override
    public UserResponseDTO getUser(long userId) {
        UserModel userModel = userMapper.getUser(userId);
        return UserResponseDTO.from(userModel);
    }

    @Override
    public void insertUser(SignupRequestDTO signupRequestDTO) {
        userMapper.insertUser(signupRequestDTO);
    }
}
