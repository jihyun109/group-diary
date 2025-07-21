package com.example.demo.service;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.dto.TeamSearchIdResponseDTO;
import com.example.demo.repository.inter.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public void insertTeam(TeamCreateRequestDTO team) {
        teamRepository.insertTeam(team);
    }

    @Override
    public List<TeamSearchIdResponseDTO> findTeamId(String teamName, long creatorId) {
        return teamRepository.findTeamId(teamName, creatorId);
    }

}
