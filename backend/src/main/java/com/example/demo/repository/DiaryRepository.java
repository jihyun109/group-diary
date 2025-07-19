package com.example.demo.repository;

import com.example.demo.model.DiaryModel;
import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.dto.TeamDiariesResponseDTO;

import java.util.List;

public interface DiaryRepository {
    void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO);

    void updateDiary(DiaryEditRequestDTO diaryEditRequestDTO);

    DiaryDetailResponseDTO requestDiaryDetails(long diaryId);
    void deleteDiary(long diaryId);
    List<TeamDiariesResponseDTO> requestAllTeamDiaries(long userId);
    List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, long writerId);
}
