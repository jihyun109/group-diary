package com.example.demo.repository;

import com.example.demo.team.TeamMapper;
import com.example.demo.team.TeamModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamRepositoryImplMybatis implements TeamRepository {
    private final TeamMapper teamMapper;

    @Override
    public List<TeamModel> searchTeamByDiaryId(long diaryId) {
        return teamMapper.searchTeamByDiaryId(diaryId);
    }
}
