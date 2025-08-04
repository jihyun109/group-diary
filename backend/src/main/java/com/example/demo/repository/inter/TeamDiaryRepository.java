package com.example.demo.repository.inter;

import com.example.demo.dto.TeamDiaryPostRequestDTO;
import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;

import java.util.List;

public interface TeamDiaryRepository {
    void insertTeamDiary(TeamDiaryPostRequestDTO teamDiary);

    void insertTeamDiaryV2(Long diaryId, List<Long> teamIds);

    void deleteTeamDiary(long diaryId, long teamId);

    void deleteTeamDiaryV2(Long diaryId, List<Long> teamIds);

    void deleteByDiaryId(Long diaryId);

    List<TeamDiaryListResponse> requestTeamDiaryList(long teamId);

    List<SharedTeamsResponse> requestSharedTeams(long diaryId);
}
