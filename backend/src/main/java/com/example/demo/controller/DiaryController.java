package com.example.demo.controller;

import com.example.demo.model.DiaryModel;
import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.service.DiaryService;
import com.example.demo.dto.TeamDiariesResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diaries")    // 일기 생성
    public ResponseEntity<String> insertDiary(@RequestBody DiaryWriteRequestDTO diary) {
        diaryService.insertDiary(diary);
        return ResponseEntity.ok("일기 작성 완료");
    }

    @PutMapping("/diaries/edit/{id}")    // 일기 수정
    public ResponseEntity<String> updateDiary(@PathVariable(required = true) long id, @RequestBody DiaryEditRequestDTO diaryData) {

        diaryService.updateDiary(id, diaryData);

        return ResponseEntity.ok("diary edited.");
    }

    @DeleteMapping("/diaries/{id}") // 일기 삭제
    public HashMap<String, String> deleteDiary(@PathVariable(required = true) int id, @RequestParam(defaultValue = "succ") String succMsg) {

        diaryService.deleteDiary(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    @GetMapping("/diaries/all/{userId}")    // 사용자가 속해있는 모든 그룹의 일기 조회
    public HashMap<String, Object> requestAllTeamDiaries(@PathVariable(required = true) int userId) {
        List<TeamDiariesResponseDTO> data = diaryService.requestAllTeamDiaries(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @GetMapping("/diaries/details/{diaryId}")   // 일기 상세 내용 요청
    public HashMap<String, Object> requestDiaryDetails(@PathVariable(required = true) int diaryId) {
        DiaryDetailResponseDTO data = diaryService.requestDiaryDetails(diaryId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 다이어리 id 요청
    @GetMapping("/diaries/findDiaryId")   // 일기 상세 내용 요청
    public HashMap<String, Object> requestDiaryId(@RequestParam(defaultValue = "succ") String diaryTitle, String writtenDate, int writerId) {
        List<DiaryModel> data = diaryService.requestDiaryId(diaryTitle, writtenDate, writerId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }
}
