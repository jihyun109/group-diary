package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.model.UserModel;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "로그인")
    @PostMapping("/users/logIn")
    public HashMap<String, Object> logIn(@RequestBody LogInRequestDTO user) {
        LoginResponseDTO userId = userService.logIn(user);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", userId);
        return result;
    }

    @Operation(summary = "사용자 정보 get")
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable long userId) {
        UserResponseDTO userResponseDTO = userService.getUser(userId);
        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "사용자 정보 수정")
    @PutMapping("/users/{id}")
    public HashMap<String, String> updateUser(@RequestBody UserUpdateRequestDTO userData, @PathVariable(required = true) long id) {
        userService.updateUser(id, userData);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @Operation(summary = "사용자 생성 (회원가입)")
    @PostMapping("/users")
    public HashMap<String, String> insertUser(@RequestBody SignupRequestDTO user) {
        userService.insertUser(user);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @Operation(summary = "사용자 삭제")
    @DeleteMapping("/users/{id}")
    public HashMap<String, String> deleteUser(@PathVariable(required = true) long id, @RequestParam(defaultValue = "succ") String succMsg) {
        userServiceImpl.deleteUser(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    @Operation(summary = "사용자 이메일 검색")
    @GetMapping("/users/search/")
    public HashMap<String, Object> userEmailSearchModel(@RequestParam(defaultValue = "succ") String searchWord) {
        String likeSearchWord = "%" + searchWord + "%";

        List<UserModel> data = userServiceImpl.userEmailSearchModel(likeSearchWord);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }
}
