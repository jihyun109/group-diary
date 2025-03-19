package com.example.demo.service.inter;

import com.example.demo.dto.response.LogInRequest;
import com.example.demo.entity.UserEntity;

import java.util.List;

public interface UserService {
    // 모든 사용자 정보 조회
    List<UserEntity> getUsers();

    // 사용자 생성
    void insertUser(UserEntity user);

    // 사용자 정보 수정
    void updateUser(int userId, UserEntity user);

    // 사용자 삭제
    void deleteUser(int userId);

    // 사용자 이메일 검색
    List<UserEntity> userEmailSearchModel(String searchWord);

    UserEntity logIn(LogInRequest user);
}
