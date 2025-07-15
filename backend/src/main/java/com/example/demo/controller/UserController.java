package com.example.demo.controller;

import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.LogInRequestDTO;
import com.example.demo.dto.UserUpdateRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.user.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final UserService userService;

    // 모든 사용자 정보 조회
    @GetMapping("/users")
    public HashMap<String, Object> getUsers() {
        List<UserModel> data = userServiceImpl.getUsers();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable long userId) {
        UserResponseDTO userResponseDTO = userService.getUser(userId);
        return ResponseEntity.ok(userResponseDTO);
    }

    // 사용자 생성
    @PostMapping("/users")
    public HashMap<String, String> insertUser(@RequestBody UserModel user) {
        userServiceImpl.insertUser(user);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 사용자 정보 수정
    @PutMapping("/users/{id}")
    public HashMap<String, String> updateUser(@RequestBody UserUpdateRequestDTO userData, @PathVariable(required = true) int id) {
        userServiceImpl.updateUser(id, userData);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 사용자 삭제
    @DeleteMapping("/users/{id}")
    public HashMap<String, String> deleteUser(@PathVariable(required = true) int id, @RequestParam(defaultValue = "succ") String succMsg) {
        userServiceImpl.deleteUser(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    // 사용자 이메일 검색
    @GetMapping("/users/search/")
    public HashMap<String, Object> userEmailSearchModel(@RequestParam(defaultValue = "succ") String searchWord) {
        String likeSearchWord = "%" + searchWord + "%";

        List<UserModel> data = userServiceImpl.userEmailSearchModel(likeSearchWord);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // login
    @PostMapping("/users/logIn")
    public HashMap<String, Object> logIn(@RequestBody LogInRequestDTO user) {
        LoginResponseDTO userId = userService.logIn(user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", userId);
        return result;
    }
}
