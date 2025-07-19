package com.example.demo.repository;

import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;
import com.example.demo.teamDiary.TeamDiaryMapper;
import com.example.demo.teamDiary.TeamDiaryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamDiaryRepositoryImplMyBatis implements TeamDiaryRepository {
    private final TeamDiaryMapper teamDiaryMapper;
    @Override
    public void insertTeamDiary(TeamDiaryModel teamDiary) {
        teamDiaryMapper.insertTeamDiary(teamDiary);
    }

    @Override
    public void deleteTeamDiary(long diaryId, long teamId) {
        teamDiaryMapper.deleteTeamDiary(diaryId, teamId);

    }

    @Override
    public List<TeamDiaryListResponse> requestTeamDiaryList(long teamId) {
        return teamDiaryMapper.requestTeamDiaryList(teamId);
    }

    @Override
    public List<SharedTeamsResponse> requestSharedTeams(long diaryId) {
        return teamDiaryMapper.requestSharedTeams(diaryId);
    }
}
