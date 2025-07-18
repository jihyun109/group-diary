package com.example.demo.service;

import com.example.demo.diary.DiaryMapper;
import com.example.demo.diary.DiaryModel;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.response.AllTeamDiariesResponse;
import com.example.demo.team.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryMapper diaryMapper;
    private final TeamMapper teamMapper;
    private final DiaryRepository diaryRepository;

    // 모든 일기 리스트 조회
    public List<DiaryModel> getDiaries() {
        return diaryMapper.selectDiaries();
    }

    // 일기 생성(일기 작성)
    @Override
    public void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO) {
        diaryRepository.insertDiary(diaryWriteRequestDTO);
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
        DiaryModel diary = diaryMapper.requestDiaryDetails(diaryId);
        diary.setSharedTeamList(teamMapper.searchTeamByDiaryId(diaryId));
        return diary;
    }

    // 일기 id 요청
    public List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId) {
        return diaryMapper.requestDiaryId(diaryTitle, writtenDate, writerId);
    }
}
