//package com.example.demo.legacy.mapperImpl;
//
//import com.example.demo.dto.response.LogInRequest;
//import com.example.demo.mapper.UserMapper;
//import com.example.demo.entity.UserModel;
//import com.example.demo.service.inter.UserService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceMapperImpl implements UserService {
//    private UserMapper userMapper;
//
//    public UserServiceMapperImpl(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }
//
//    // 모든 사용자 정보 조회
//    @Override
//    public List<UserModel> getUsers() {
//        return userMapper.selectUsers();
//    }
//
//    // 사용자 생성
//    @Override
//    public void insertUser(UserModel user) {
//        userMapper.insertUser(user);
//    }
//
//    // 사용자 정보 수정
//    @Override
//    public void updateUser(int userId, UserModel user) {
//        user.setId(userId);
//        userMapper.updateUser(user);
//    }
//
//    // 사용자 삭제
//    @Override
//    public void deleteUser(int userId) {
//        userMapper.deleteUser(userId);
//    }
//
//    // 사용자 이름 요청
////    public String requestUserName(int userId) {
////        return userMapper.requestUserName(userId);
////    }
//
//    // 사용자 정보 요청
////    public UserModel requestUserInfo(int userId) {
////        return userMapper.requestUserInfo(userId);
////    }
//
//    // 사용자 이메일 검색
//    @Override
//    public List<UserModel> userEmailSearchModel(String searchWord) {
//        return userMapper.userEmailSearchModel(searchWord);
//    }
//
//    @Override
//    public UserModel logIn(LogInRequest user) {
//        return userMapper.logIn(user);
//    }
//}
