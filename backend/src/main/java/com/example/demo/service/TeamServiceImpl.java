package com.example.demo.service;

import com.example.demo.mapper.TeamMapper;
import com.example.demo.model.TeamModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamMapper teamMapper;

    @Override
    public void insertTeam(TeamModel team) {
        teamMapper.insertTeam(team);
    }

    @Override
    public List<TeamModel> findTeamId(String teamName, long creatorId) {
        return teamMapper.findTeamId(teamName, creatorId);
    }

}
