//package com.example.demo.service.impl;
//
//import com.example.demo.dto.request.SharedTeamsResponse;
//import com.example.demo.dto.request.TeamDiaryListResponse;
//import com.example.demo.entity.TeamDiaryEntity;
//import com.example.demo.mapper.TeamDiaryMapper;
//import com.example.demo.service.inter.TeamDiaryService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TeamDiaryServiceImpl implements TeamDiaryService {
//    private com.example.demo.mapper.TeamDiaryMapper TeamDiaryMapper;
//
//    public TeamDiaryServiceImpl(TeamDiaryMapper TeamDiaryMapper){
//        this.TeamDiaryMapper = TeamDiaryMapper;
//    }
//
//    // 모든 팀 일기 리스트 조회
//    @Override
//    public List<TeamDiaryEntity> getTeamDiaries() {
//        return TeamDiaryMapper.selectTeamDiaries();
//    }
//
//    // 팁 일기 생성
//    @Override
//    public void insertTeamDiary(TeamDiaryEntity teamDiary) {
//        TeamDiaryMapper.insertTeamDiary(teamDiary);
//    }
//
//    // 팀 일기 수정
//    @Override
//    public void updateTeamDiary(int teamDiaryId, TeamDiaryEntity teamDiary) {
//        teamDiary.setId(teamDiaryId);
//        TeamDiaryMapper.updateTeamDiary(teamDiary);
//    }
//
//    // 팀 일기 삭제 (공유 해제)
//    @Override
//    public void deleteTeamDiary(int diaryId, int teamId) {
//        TeamDiaryMapper.deleteTeamDiary(diaryId, teamId);
//    }
//
//    // 현재 팀에 공유된 일기 리스트 요청
//    @Override
//    public List<TeamDiaryListResponse> requestTeamDiaryList(int teamId) {
//        return TeamDiaryMapper.requestTeamDiaryList(teamId);
//    }
//
//    // 선택한 일기가 공유된 팀들의 id, 이름 요청
//    @Override
//    public List<SharedTeamsResponse> requestSharedTeams(int diaryId) {
//        return TeamDiaryMapper.requestSharedTeams(diaryId);
//    }
//
//
//}
