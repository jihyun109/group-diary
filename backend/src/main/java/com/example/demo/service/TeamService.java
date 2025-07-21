package com.example.demo.service;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.dto.TeamSearchIdResponseDTO;

import java.util.List;

public interface TeamService {
    void insertTeam(TeamCreateRequestDTO team);

    List<TeamSearchIdResponseDTO> findTeamId(String teamName, long creatorId);
}
