//package com.example.demo.mapper;
//
//import com.example.demo.entity.TeamDiaryModel;
//import com.example.demo.dto.request.SharedTeamsResponse;
//import com.example.demo.dto.request.TeamDiaryListResponse;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface TeamDiaryMapper {
//
//    // 모든 팀 일기 리스트 조회
//    List<TeamDiaryModel> selectTeamDiaries();
//
//    // 팁 일기 생성
//    void insertTeamDiary(TeamDiaryModel teamDiary);
//
//    // 팀 일기 수정
//    void updateTeamDiary(TeamDiaryModel teamDiary);
//
//    // 팀 일기 삭제 (공유 해제)
//    void deleteTeamDiary(int diaryId, int teamId);
//
//    // 현재 팀에 공유된 일기 리스트 요청
//    List<TeamDiaryListResponse> requestTeamDiaryList(int teamId);
//
//    // 선택한 일기가 공유된 팀들의 id, 이름 요청
//    List<SharedTeamsResponse> requestSharedTeams(int diaryId);
//}
