package com.example.demo.service.impl;

import com.example.demo.dto.request.AllTeamDiariesResponse;
import com.example.demo.entity.DiaryModel;
import com.example.demo.mapper.DiaryMapper;
import com.example.demo.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl {
    private DiaryMapper diaryMapper;
    private TeamMapper teamMapper;

    public DiaryServiceImpl(DiaryMapper diaryMapper, TeamMapper teamMapper) {
        this.teamMapper=teamMapper;
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
    public DiaryModel requestDiaryDetails(int diaryId) {
        DiaryModel diary= diaryMapper.requestDiaryDetails(diaryId);
        diary.setSharedTeamList(teamMapper.searchTeamByDiaryId(diaryId));
        return diary;
    }

    // 일기 id 요청
    public List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId) {
        return diaryMapper.requestDiaryId(diaryTitle, writtenDate, writerId);
    }



}
