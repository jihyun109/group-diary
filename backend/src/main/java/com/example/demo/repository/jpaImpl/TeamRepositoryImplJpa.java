package com.example.demo.repository.jpaImpl;

import com.example.demo.dto.TeamDiaryPostRequestDTO;
import com.example.demo.repository.inter.TeamDiaryRepository;
import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;

import java.util.List;

public class TeamRepositoryImplJpa implements TeamDiaryRepository {
    @Override
    public void insertTeamDiary(TeamDiaryPostRequestDTO teamDiary) {

    }

    @Override
    public void deleteTeamDiary(long diaryId, long teamId) {

    }

    @Override
    public List<TeamDiaryListResponse> requestTeamDiaryList(long teamId) {
        return null;
    }

    @Override
    public List<SharedTeamsResponse> requestSharedTeams(long diaryId) {
        return null;
    }
}
