package com.example.demo.repository.jpaImpl;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.dto.TeamSearchIdResponseDTO;
import com.example.demo.model.TeamModel;
import com.example.demo.repository.inter.TeamRepository;

import java.util.List;

public class TeamRepositoryImplJpa implements TeamRepository {

    @Override
    public List<TeamModel> searchTeamByDiaryId(long diaryId) {
        return null;
    }

    @Override
    public void insertTeam(TeamCreateRequestDTO team) {

    }

    @Override
    public List<TeamSearchIdResponseDTO> findTeamId(String teamName, long creatorId) {
        return null;
    }
}
