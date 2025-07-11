package com.example.demo.service;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.dto.LogInRequestDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    // 모든 사용자 정보 조회
    public List<UserEntity> getUsers() {
        return userMapper.selectUsers();
    }

    // 사용자 생성
    public void insertUser(UserEntity user) {
        userMapper.insertUser(user);
    }

    // 사용자 정보 수정
    public void updateUser(int userId, UserEntity user) {
        user.setId(userId);
        userMapper.updateUser(user);
    }

    // 사용자 삭제
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    // 사용자 이메일 검색
    public List<UserEntity> userEmailSearchModel(String searchWord) {
        return userMapper.userEmailSearchModel(searchWord);
    }

    public LoginResponseDTO logIn(LogInRequestDTO user) {
        return userRepository.login(user);
    }
}
