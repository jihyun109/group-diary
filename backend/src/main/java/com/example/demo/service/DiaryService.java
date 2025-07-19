package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

public interface DiaryService {
    void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO);

    void updateDiary(long id, DiaryEditRequestDTO diaryData);

    DiaryDetailResponseDTO requestDiaryDetails(long diaryId);

    void deleteDiary(long diaryId);

    List<TeamDiariesResponseDTO> requestAllTeamDiaries(long userId);

    List<DiaryIdResponseDTO> requestDiaryId(String diaryTitle, String writtenDate, long writerId);
}
