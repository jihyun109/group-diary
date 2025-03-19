package com.example.demo.service.inter;

import com.example.demo.dto.request.SharedTeamsResponse;
import com.example.demo.dto.request.TeamDiaryListResponse;
import com.example.demo.entity.TeamDiaryEntity;

import java.util.List;

public interface TeamDiaryService {
    // 모든 팀 일기 리스트 조회
    List<TeamDiaryEntity> getTeamDiaries();

    // 팁 일기 생성
    void insertTeamDiary(TeamDiaryEntity teamDiary);

    // 팀 일기 수정
    void updateTeamDiary(int teamDiaryId, TeamDiaryEntity teamDiary);

    // 팀 일기 삭제 (공유 해제)
    void deleteTeamDiary(int diaryId, int teamId);

    // 현재 팀에 공유된 일기 리스트 요청
    List<TeamDiaryListResponse> requestTeamDiaryList(int teamId);

    // 선택한 일기가 공유된 팀들의 id, 이름 요청
    List<SharedTeamsResponse> requestSharedTeams(int diaryId);
}
