package com.example.demo.mapper;

import com.example.demo.dto.LogInRequestDTO;
import com.example.demo.dto.SignupRequestDTO;
import com.example.demo.dto.UserUpdateRequestDTO;
import com.example.demo.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    // 사용자 생성
    void insertUser(SignupRequestDTO user);

    // 사용자 정보 수정
    void updateUser(UserUpdateRequestDTO user);

    // 사용자 삭제
    void deleteUser(int userId);

    // 사용자 이메일 검색
    List<UserModel> userEmailSearchModel(String searchWord);

    // 로그인 (성공 -> userId 반환)
    UserModel logIn(LogInRequestDTO user);

    UserModel getUser(@Param("userId") long userId);
}
