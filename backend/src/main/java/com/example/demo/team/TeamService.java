package com.example.demo.team;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private TeamMapper teamMapper;

    public TeamService(TeamMapper teamMapper){
        this.teamMapper = teamMapper;
    }

    public List<TeamModel> getTeams() {
        return teamMapper.selectTeams();
    }

    public void insertTeam(TeamModel team) {
        teamMapper.insertTeam(team);
    }

    public void updateTeam(int teamId, TeamModel team) {
        team.setId(teamId);
        teamMapper.updateTeam(team);
    }

    public void deleteTeam(int teamId) {
        teamMapper.deleteTeam(teamId);
    }

    // 팀 이름으로 팀 검색
    public List<TeamModel> searchTeam(String searchWord) {
        return teamMapper.searchTeam(searchWord);
    }

    public List<TeamModel> searchTeamByDiaryId(int diaryId) {
        return teamMapper.searchTeamByDiaryId(diaryId);
    }

    public List<TeamModel> findTeamId(String teamName, int creatorId) {
        return teamMapper.findTeamId(teamName, creatorId);
    }

    // 사용자가 속한 팀 만들기 요청
//    public void requestCreateTeam(int userId, CreateTeamRequest team) {
//        teamMapper.requestCreateTeam(team);
//    }

}
