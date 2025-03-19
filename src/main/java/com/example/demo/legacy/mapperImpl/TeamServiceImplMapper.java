//package com.example.demo.legacy.mapperImpl;
//
//import com.example.demo.mapper.TeamMapper;
//import com.example.demo.entity.TeamModel;
//import com.example.demo.service.inter.TeamService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TeamServiceImplMapper implements TeamService {
//    private TeamMapper teamMapper;
//
//    public TeamServiceImplMapper(TeamMapper teamMapper){
//        this.teamMapper = teamMapper;
//    }
//
//    @Override
//    public List<TeamModel> getTeams() {
//        return teamMapper.selectTeams();
//    }
//
//    @Override
//    public void insertTeam(TeamModel team) {
//        teamMapper.insertTeam(team);
//    }
//
//    @Override
//    public void updateTeam(int teamId, TeamModel team) {
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
//    public List<TeamModel> searchTeam(String searchWord) {
//        return teamMapper.searchTeam(searchWord);
//    }
//
//    @Override
//    public List<TeamModel> searchTeamByDiaryId(int diaryId) {
//        return teamMapper.searchTeamByDiaryId(diaryId);
//    }
//
//    @Override
//    public List<TeamModel> findTeamId(String teamName, int creatorId) {
//        return teamMapper.findTeamId(teamName, creatorId);
//    }
//
//    // 사용자가 속한 팀 만들기 요청
////    public void requestCreateTeam(int userId, CreateTeamRequest team) {
////        teamMapper.requestCreateTeam(team);
////    }
//
//}
