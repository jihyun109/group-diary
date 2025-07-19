package com.example.demo.controller;

import com.example.demo.model.TeamModel;
import com.example.demo.service.TeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamServiceImpl teamServiceImpl;

    // 팀 생성
    @PostMapping("/teams")
    public HashMap<String, String> insertTeam(@RequestBody TeamModel team) {
        teamServiceImpl.insertTeam(team);
        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 팀 id 조회
    @GetMapping("/teams/findId")
    public HashMap<String, Object> findTeamId(@RequestParam(defaultValue = "succ") String teamName, int creatorId) {
        List<TeamModel> data = teamServiceImpl.findTeamId(teamName, creatorId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

}
