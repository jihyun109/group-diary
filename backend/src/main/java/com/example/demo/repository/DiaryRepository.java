package com.example.demo.repository;

import com.example.demo.model.DiaryModel;
import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.response.AllTeamDiariesResponse;

import java.util.List;

public interface DiaryRepository {
    void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO);

    void updateDiary(DiaryEditRequestDTO diaryEditRequestDTO);

    DiaryDetailResponseDTO requestDiaryDetails(long diaryId);
    void deleteDiary(int diaryId);
    List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId);
    List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId);
}
