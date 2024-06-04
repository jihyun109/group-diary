package com.example.demo;

import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;
import com.example.demo.teamDiary.TeamDiaryModel;
import com.example.demo.teamDiary.TeamDiaryService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TeamDiaryController {

    private TeamDiaryService teamDiaryService;

    public TeamDiaryController(TeamDiaryService teamDiaryService) {
        this.teamDiaryService = teamDiaryService;
    }

    // 모든 팀 일기 리스트 조회
    @GetMapping("/teamDiaries")
    public HashMap<String, Object> getTeamDiaries() {
        List<TeamDiaryModel> data = teamDiaryService.getTeamDiaries();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 팁 일기 생성
    @PostMapping("/teamDiaries")
    public HashMap<String, String> insertTeamDiary(@RequestBody TeamDiaryModel teamDiary) {

        teamDiaryService.insertTeamDiary(teamDiary);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 팀 일기 수정
    @PutMapping("/teamDiaries/{id}")
    public HashMap<String, String> updateTeamDiary(@RequestBody TeamDiaryModel teamDiaryData, @PathVariable(required = true) int id) {

        teamDiaryService.updateTeamDiary(id, teamDiaryData);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 팀 일기 삭제 (공유 해제)
    @DeleteMapping("/teamDiaries/{id}")
    public HashMap<String, String> deleteTeamDiary(@PathVariable(required = true) int id, @RequestParam(defaultValue = "succ") String succMsg) {

        teamDiaryService.deleteTeamDiary(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    // 현재 팀에 공유된 일기 리스트 요청
    @GetMapping("/teamDiaries/diaryList/{teamId}")
    public HashMap<String, Object> requestTeamDiaryList(@PathVariable(required = true) int teamId) {
        List<TeamDiaryListResponse> data = teamDiaryService.requestTeamDiaryList(teamId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 선택한 일기가 공유된 팀들의 id, 이름 요청
    @GetMapping("/teamDiaries/sharedTeams/{diaryId}")
    public HashMap<String, Object> requestSharedTeams(@PathVariable(required = true) int diaryId) {
        List<SharedTeamsResponse> data = teamDiaryService.requestSharedTeams(diaryId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

}
