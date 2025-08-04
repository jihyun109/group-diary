package com.example.demo.repository.inter;

import com.example.demo.dto.*;

import java.util.List;

public interface DiaryRepository {
    void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO);

    long createDiary(DiaryWriteRequestDTOv2 diaryWriteRequestDTOv2);

    void updateDiary(DiaryEditRequestDTO diaryEditRequestDTO);

    DiaryDetailResponseDTO requestDiaryDetails(long diaryId);

    void deleteDiary(long diaryId);

    List<TeamDiariesResponseDTO> requestAllTeamDiaries(long userId);

    List<DiaryIdResponseDTO> requestDiaryId(String diaryTitle, String writtenDate, long writerId);
}
