package com.example.demo.service;

import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;

public interface DiaryService {
    void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO);
    void updateDiary(long id, DiaryEditRequestDTO diaryData);
    DiaryDetailResponseDTO requestDiaryDetails(long diaryId);
}
