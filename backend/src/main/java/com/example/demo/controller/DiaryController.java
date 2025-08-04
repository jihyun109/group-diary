package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(summary = "일기 생성")
    @PostMapping("/diaries")    // 일기 생성
    public ResponseEntity<String> insertDiary(@RequestBody DiaryWriteRequestDTO diary) {
        diaryService.insertDiary(diary);
        return ResponseEntity.ok("일기 작성 완료");
    }

    @Operation(summary = "일기 작성")
    @PostMapping("/v2/diaries")
    public ResponseEntity<String> writeDiary(@RequestBody DiaryWriteRequestDTOv2 diary) {
        diaryService.writeDiary(diary);
        return ResponseEntity.ok("일기 작성 완료");
    }

    @Operation(summary = "일기 수정")
    @PutMapping("/diaries/edit/{id}")
    public ResponseEntity<String> updateDiary(@PathVariable(required = true) long id, @RequestBody DiaryEditRequestDTO diaryData) {

        diaryService.updateDiary(id, diaryData);

        return ResponseEntity.ok("diary edited.");
    }

    @Operation(summary = "일기 수정")
    @PutMapping("/v2/diaries/edit/{id}")
    public ResponseEntity<String> updateDiary(@RequestBody DiaryEditRequestDTOv2 diaryEditRequestDTOv2) {
        diaryService.updateDiary(diaryEditRequestDTOv2);

        return ResponseEntity.ok("diary edited.");
    }

    @Operation(summary = "일기 삭제")
    @DeleteMapping("/diaries/{id}")
    public HashMap<String, String> deleteDiary(@PathVariable(required = true) long id, @RequestParam(defaultValue = "succ") String succMsg) {

        diaryService.deleteDiary(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    @Operation(summary = "사용자가 속해있는 모든 그룹의 일기 조회")
    @GetMapping("/diaries/all/{userId}")
    public HashMap<String, Object> requestAllTeamDiaries(@PathVariable(required = true) long userId) {
        List<TeamDiariesResponseDTO> data = diaryService.requestAllTeamDiaries(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @Operation(summary = "일기 상세 내용 요청")
    @GetMapping("/diaries/details/{diaryId}")
    public HashMap<String, Object> requestDiaryDetails(@PathVariable(required = true) long diaryId) {
        DiaryDetailResponseDTO data = diaryService.requestDiaryDetails(diaryId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 다이어리 id 요청
    @GetMapping("/diaries/findDiaryId")
    public HashMap<String, Object> requestDiaryId(@RequestParam(defaultValue = "succ") String diaryTitle, String writtenDate, long writerId) {
        List<DiaryIdResponseDTO> data = diaryService.requestDiaryId(diaryTitle, writtenDate, writerId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }
}
