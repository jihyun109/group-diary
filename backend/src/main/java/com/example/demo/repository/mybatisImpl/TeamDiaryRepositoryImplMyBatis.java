package com.example.demo.repository.mybatisImpl;

import com.example.demo.dto.TeamDiaryPostRequestDTO;
import com.example.demo.repository.inter.TeamDiaryRepository;
import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;
import com.example.demo.mapper.TeamDiaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Primary
public class TeamDiaryRepositoryImplMyBatis implements TeamDiaryRepository {
    private final TeamDiaryMapper teamDiaryMapper;

    @Override
    public void insertTeamDiary(TeamDiaryPostRequestDTO teamDiary) {
        teamDiaryMapper.insertTeamDiary(teamDiary);
    }

    @Override
    public void insertTeamDiaryV2(Long diaryId, List<Long> teamIds) {
        teamDiaryMapper.insertTeamDiaryV2(diaryId, teamIds);
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
