package com.example.demo.service;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.model.TeamModel;

import java.util.List;

public interface TeamService {
    void insertTeam(TeamCreateRequestDTO team);

    List<TeamModel> findTeamId(String teamName, long creatorId);
}
