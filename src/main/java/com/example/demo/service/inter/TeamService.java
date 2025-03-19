package com.example.demo.service.inter;

import com.example.demo.entity.TeamEntity;

import java.util.List;

public interface TeamService {
    List<TeamEntity> getTeams();

    void insertTeam(TeamEntity team);

    void updateTeam(int teamId, TeamEntity team);

    void deleteTeam(int teamId);

    // 팀 이름으로 팀 검색
    List<TeamEntity> searchTeam(String searchWord);

    List<TeamEntity> searchTeamByDiaryId(int diaryId);

    List<TeamEntity> findTeamId(String teamName, int creatorId);
}
