package com.example.demo.controller;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.inter.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

// TODO: 사용자 인증 적용 후 사용자 id 받아오는 것 변경
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 모든 사용자 정보 조회
    @GetMapping("/users")
    public HashMap<String, Object> getUsers() {
        List<UserEntity> data = userService.getUsers();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 사용자 생성
    @PostMapping("/users")
    public HashMap<String, String> insertUser(@RequestBody UserEntity user) {
        userService.insertUser(user);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 사용자 정보 수정
    @PutMapping("/users/{id}")
    public HashMap<String, String> updateUser(@RequestBody UserEntity userData, @PathVariable(required = true) int id) {
        userService.updateUser(id, userData);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 사용자 삭제
    @DeleteMapping("/users/{id}")
    public HashMap<String, String> deleteUser(@PathVariable(required = true) int id, @RequestParam(defaultValue = "succ") String succMsg) {
        userService.deleteUser(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    // 사용자 이름 요청
//    @GetMapping("/users/name/{userId}")
//    public HashMap<String, Object> requestUserName(@PathVariable(required = true) int userId) {
//        String data = userService.requestUserName(userId);
//
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("result", "success");
//        result.put("data", data);
//        return result;
//    }

//    @GetMapping("/user/info/{userId}")
//    public HashMap<String, Object> requestUserInfo(@PathVariable(required = true) int userId) {
//        UserModel data = userService.requestUserInfo(userId);
//
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("result", "success");
//        result.put("data", data);
//        return result;
//    }

    // 사용자 이메일 검색
    @GetMapping("/users/search/")
    public HashMap<String, Object> userEmailSearchModel(@RequestParam(defaultValue = "succ") String searchWord) {
        String likeSearchWord = "%" + searchWord + "%";

        List<UserEntity> data = userService.userEmailSearchModel(likeSearchWord);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // login
    @PostMapping("/users/logIn")
    public HashMap<String, Object> logIn(@RequestBody LogInRequest user) {
        UserEntity userId = userService.logIn(user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", userId);
        return result;
    }
}
