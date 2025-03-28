package com.example.demo.service.inter;

import com.example.demo.dto.response.AllTeamDiariesResponse;
import com.example.demo.entity.DiaryModel;

import java.util.List;

public interface DiaryService {

    // 모든 일기 리스트 조회
    List<DiaryModel> getDiaries();

    // 일기 생성
    void insertDiary(DiaryModel diary);

    // 일기 수정
    void updateDiary(int diaryId, DiaryModel diary);

    // 일기 삭제
    void deleteDiary(int diaryId);

    // 사용자가 포함된 모든 그룹의 일기 정보 요청
    List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId);

    // 선택한 다이어리 상세 정보 요청
    DiaryModel requestDiaryDetails(int diaryId);

    // 일기 id 요청
    List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId);
}
