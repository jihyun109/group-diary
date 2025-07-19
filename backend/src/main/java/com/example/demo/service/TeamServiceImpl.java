package com.example.demo.service;

import com.example.demo.model.TeamModel;
import com.example.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public void insertTeam(TeamModel team) {
        teamRepository.insertTeam(team);
    }

    @Override
    public List<TeamModel> findTeamId(String teamName, long creatorId) {
        return teamRepository.findTeamId(teamName, creatorId);
    }

}
