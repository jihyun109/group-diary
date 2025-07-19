package com.example.demo.service;

import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;
import com.example.demo.teamDiary.TeamDiaryModel;

import java.util.List;

public interface TeamDiaryService {
    List<TeamDiaryModel> getTeamDiaries();

    void insertTeamDiary(TeamDiaryModel teamDiary);

    void deleteTeamDiary(int diaryId, int teamId);

    List<TeamDiaryListResponse> requestTeamDiaryList(int teamId);

    List<SharedTeamsResponse> requestSharedTeams(int diaryId);
}
