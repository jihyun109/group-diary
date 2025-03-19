//package com.example.demo.service.impl;
//
//import com.example.demo.entity.TeamEntity;
//import com.example.demo.mapper.TeamMapper;
//import com.example.demo.service.inter.TeamService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TeamServiceImpl implements TeamService {
//    private TeamMapper teamMapper;
//
//    public TeamServiceImpl(TeamMapper teamMapper){
//        this.teamMapper = teamMapper;
//    }
//
//    @Override
//    public List<TeamEntity> getTeams() {
//        return teamMapper.selectTeams();
//    }
//
//    @Override
//    public void insertTeam(TeamEntity team) {
//        teamMapper.insertTeam(team);
//    }
//
//    @Override
//    public void updateTeam(int teamId, TeamEntity team) {
//        team.setId(teamId);
//        teamMapper.updateTeam(team);
//    }
//
//    @Override
//    public void deleteTeam(int teamId) {
//        teamMapper.deleteTeam(teamId);
//    }
//
//    // 팀 이름으로 팀 검색
//    @Override
//    public List<TeamEntity> searchTeam(String searchWord) {
//        return teamMapper.searchTeam(searchWord);
//    }
//
//    @Override
//    public List<TeamEntity> searchTeamByDiaryId(int diaryId) {
//        return teamMapper.searchTeamByDiaryId(diaryId);
//    }
//
//    @Override
//    public List<TeamEntity> findTeamId(String teamName, int creatorId) {
//        return teamMapper.findTeamId(teamName, creatorId);
//    }
//
//    // 사용자가 속한 팀 만들기 요청
////    public void requestCreateTeam(int userId, CreateTeamRequest team) {
////        teamMapper.requestCreateTeam(team);
////    }
//
//}
