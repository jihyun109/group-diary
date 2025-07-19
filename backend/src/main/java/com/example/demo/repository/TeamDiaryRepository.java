package com.example.demo.repository;

import com.example.demo.dto.TeamDiaryPostRequestDTO;
import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;

import java.util.List;

public interface TeamDiaryRepository {
    void insertTeamDiary(TeamDiaryPostRequestDTO teamDiary);

    void deleteTeamDiary(long diaryId, long teamId);

    List<TeamDiaryListResponse> requestTeamDiaryList(long teamId);

    List<SharedTeamsResponse> requestSharedTeams(long diaryId);
}
