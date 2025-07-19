package com.example.demo.repository;

import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;

public interface DiaryRepository {
    void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO);

    void updateDiary(DiaryEditRequestDTO diaryEditRequestDTO);

    DiaryDetailResponseDTO requestDiaryDetails(long diaryId);
}
