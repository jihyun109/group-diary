package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.request.LogInRequest;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();
    void insertUser(UserEntity user);
    void updateUser(int userId, UserEntity user);
    void deleteUser(int userId);
    List<UserEntity> userEmailSearchModel(String searchWord);
    UserEntity logIn(LogInRequest user);
}
