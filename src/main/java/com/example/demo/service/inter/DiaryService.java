package com.example.demo.service.inter;

import com.example.demo.dto.request.DiaryCreateRequest;
import com.example.demo.dto.request.DiaryUpdateRequest;
import com.example.demo.dto.response.AllTeamDiariesResponse;
import com.example.demo.dto.response.DiaryDetailResponse;
import com.example.demo.dto.response.DiaryIdResponse;
import com.example.demo.dto.response.DiaryListResponse;
import com.example.demo.entity.DiaryEntity;

import java.util.List;

public interface DiaryService {

    // 모든 일기 리스트 조회
    List<DiaryListResponse> getDiaries();

    // 일기 생성
    void insertDiary(DiaryCreateRequest diary);

    // 일기 수정
    void updateDiary(Long diaryId, DiaryUpdateRequest diary);

    // 일기 삭제
    void deleteDiary(Long diaryId);

    // 사용자가 포함된 모든 그룹의 일기 정보 요청
    List<AllTeamDiariesResponse> requestAllTeamDiaries(Long userId);

    // 선택한 다이어리 상세 정보 요청
    DiaryDetailResponse requestDiaryDetails(Long diaryId);

    // 일기 id 요청
    Long requestDiaryId(String diaryTitle, String writtenDate, Long writerId);
}
