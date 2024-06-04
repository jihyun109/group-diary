package com.example.demo.diary;

import com.example.demo.response.AllTeamDiariesResponse;
import com.example.demo.response.DiaryDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {
    private DiaryMapper diaryMapper;

    public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    // 모든 일기 리스트 조회
    public List<DiaryModel> getDiaries() {
        return diaryMapper.selectDiaries();
    }

    // 일기 생성
    public void insertDiary(DiaryModel diary) {
        diaryMapper.insertDiary(diary);
    }

    // 일기 수정
    public void updateDiary(int diaryId, DiaryModel diary) {
        diary.setId(diaryId);
        diaryMapper.updateDiary(diary);
    }

    // 일기 삭제
    public void deleteDiary(int diaryId) {
        diaryMapper.deleteDiary(diaryId);
    }

    // 사용자가 포함된 모든 그룹의 일기 정보 요청
    public List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId) {
        return diaryMapper.requestAllTeamDiaries(userId);
    }

    // 선택한 다이어리 상세 정보 요청
    public DiaryDetailsResponse requestDiaryDetails(int diaryId) {
        return diaryMapper.requestDiaryDetails(diaryId);
    }


}
