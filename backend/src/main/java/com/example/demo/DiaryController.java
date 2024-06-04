package com.example.demo;

import com.example.demo.diary.DiaryModel;
import com.example.demo.diary.DiaryService;
import com.example.demo.response.AllTeamDiariesResponse;
import com.example.demo.response.DiaryDetailsResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class DiaryController {

    private DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/diaries") // 모든 일기 리스트 조회
    public HashMap<String, Object> getDiaries() {
        List<DiaryModel> data = diaryService.getDiaries();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @PostMapping("/diaries")    // 일기 생성
    public HashMap<String, String> insertDiary(@RequestBody DiaryModel diary) {

        diaryService.insertDiary(diary);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    @PutMapping("/diaries/edit/{id}")    // 일기 수정
    public HashMap<String, String> updateDiary(@PathVariable(required = true) int id, @RequestBody DiaryModel diaryData) {

        diaryService.updateDiary(id, diaryData);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
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
        List<AllTeamDiariesResponse> data = diaryService.requestAllTeamDiaries(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @GetMapping("/diaries/details/{diaryId}")   // 일기 상세 내용 요청
    public HashMap<String, Object> requestDiaryDetails(@PathVariable(required = true) int diaryId) {
        DiaryDetailsResponse data = diaryService.requestDiaryDetails(diaryId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }


}
