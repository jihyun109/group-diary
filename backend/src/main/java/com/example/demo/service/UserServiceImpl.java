package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.repository.inter.UserRepository;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO getUser(long userId) {
        return userRepository.getUser(userId);
    }

    // 사용자 생성
    @Override
    public void insertUser(SignupRequestDTO user) {
        userRepository.insertUser(user);
    }

    @Override
    // 사용자 정보 수정
    public void updateUser(long userId, UserUpdateRequestDTO user) {
        user.setId(userId);
        userRepository.updateUser(user);
    }

    @Override
    // 사용자 삭제
    public void deleteUser(long userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    // 사용자 이메일 검색
    public List<UserModel> userEmailSearchModel(String searchWord) {
        return userMapper.userEmailSearchModel(searchWord);
    }

    @Override
    public LoginResponseDTO logIn(LogInRequestDTO user) {
        return userRepository.login(user);
    }
}
