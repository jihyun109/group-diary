package com.example.demo.controller;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.model.TeamModel;
import com.example.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // 팀 생성
    @PostMapping("/teams")
    public HashMap<String, String> insertTeam(@RequestBody TeamCreateRequestDTO team) {
        teamService.insertTeam(team);
        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 팀 id 조회
    @GetMapping("/teams/findId")
    public HashMap<String, Object> findTeamId(@RequestParam(defaultValue = "succ") String teamName, int creatorId) {
        List<TeamModel> data = teamService.findTeamId(teamName, creatorId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

}
