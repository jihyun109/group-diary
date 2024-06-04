package com.example.demo.user;

import com.example.demo.request.LogInRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    // 모든 사용자 정보 조회
    public List<UserModel> getUsers() {
        return userMapper.selectUsers();
    }

    // 사용자 생성
    public void insertUser(UserModel user) {
        userMapper.insertUser(user);
    }

    // 사용자 정보 수정
    public void updateUser(int userId, UserModel user) {
        user.setId(userId);
        userMapper.updateUser(user);
    }

    // 사용자 삭제
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    // 사용자 이름 요청
//    public String requestUserName(int userId) {
//        return userMapper.requestUserName(userId);
//    }

    // 사용자 정보 요청
//    public UserModel requestUserInfo(int userId) {
//        return userMapper.requestUserInfo(userId);
//    }

    // 사용자 이메일 검색
    public List<UserModel> userEmailSearchModel(String searchWord) {
        return userMapper.userEmailSearchModel(searchWord);
    }

    public UserModel logIn(LogInRequest user) {
        return userMapper.logIn(user);
    }
}
