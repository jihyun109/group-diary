package com.example.demo.service.inter;

import com.example.demo.entity.TeamModel;

import java.util.List;

public interface TeamService {
    List<TeamModel> getTeams();

    void insertTeam(TeamModel team);

    void updateTeam(int teamId, TeamModel team);

    void deleteTeam(int teamId);

    // 팀 이름으로 팀 검색
    List<TeamModel> searchTeam(String searchWord);

    List<TeamModel> searchTeamByDiaryId(int diaryId);

    List<TeamModel> findTeamId(String teamName, int creatorId);
}
