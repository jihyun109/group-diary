//package com.example.demo.service.impl;
//
//import com.example.demo.dto.request.AllTeamDiariesResponse;
//import com.example.demo.entity.DiaryEntity;
//import com.example.demo.entity.TeamEntity;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DiaryServiceImpl {
//    private DiaryEntity diaryEntity;
//    private TeamEntity teamEntity;
//
//    public DiaryServiceImpl(DiaryEntity diaryEntity, TeamEntity teamEntity) {
//        this.teamEntity=teamEntity;
//        this.diaryEntity = diaryEntity;
//    }
//
//    // 모든 일기 리스트 조회
//    public List<DiaryEntity> getDiaries() {
//        return diaryEntity.selectDiaries();
//    }
//
//    // 일기 생성
//    public void insertDiary(DiaryEntity diary) {
//        diaryEntity.insertDiary(diary);
//    }
//
//    // 일기 수정
//    public void updateDiary(int diaryId, DiaryEntity diary) {
//        diary.setId(diaryId);
//        diaryEntity.updateDiary(diary);
//    }
//
//    // 일기 삭제
//    public void deleteDiary(int diaryId) {
//        diaryEntity.deleteDiary(diaryId);
//    }
//
//    // 사용자가 포함된 모든 그룹의 일기 정보 요청
//    public List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId) {
//        return diaryEntity.requestAllTeamDiaries(userId);
//    }
//
//    // 선택한 다이어리 상세 정보 요청
//    public DiaryEntity requestDiaryDetails(int diaryId) {
//        DiaryEntity diary= diaryEntity.requestDiaryDetails(diaryId);
////        diary.setSharedTeamList(TeamEntity.searchTeamByDiaryId(diaryId));
//        return diary;
//    }
//
//    // 일기 id 요청
//    public List<DiaryEntity> requestDiaryId(String diaryTitle, String writtenDate, int writerId) {
//        return diaryEntity.requestDiaryId(diaryTitle, writtenDate, writerId);
//    }
//
//
//
//}
