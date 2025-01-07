package com.example.demo.service.inter;

import com.example.demo.dto.response.LogInRequest;
import com.example.demo.entity.UserModel;

import java.util.List;

public interface UserService {
    // 모든 사용자 정보 조회
    List<UserModel> getUsers();

    // 사용자 생성
    void insertUser(UserModel user);

    // 사용자 정보 수정
    void updateUser(int userId, UserModel user);

    // 사용자 삭제
    void deleteUser(int userId);

    // 사용자 이메일 검색
    List<UserModel> userEmailSearchModel(String searchWord);

    UserModel logIn(LogInRequest user);
}
