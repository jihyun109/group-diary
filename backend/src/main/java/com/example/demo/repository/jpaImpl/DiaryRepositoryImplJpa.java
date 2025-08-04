package com.example.demo.repository.jpaImpl;

import com.example.demo.dto.*;
import com.example.demo.repository.inter.DiaryRepository;

import java.util.List;

public class DiaryRepositoryImplJpa implements DiaryRepository {
    @Override
    public void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO) {

    }

    @Override
    public long createDiary(DiaryWriteRequestDTOv2 diaryWriteRequestDTOv2) {
        return 0;
    }

    @Override
    public void updateDiary(DiaryEditRequestDTO diaryEditRequestDTO) {

    }

    @Override
    public void updateDiaryV2(DiaryEditRequestDTOv2 diaryEditRequestDTOv2) {

    }

    @Override
    public DiaryDetailResponseDTO requestDiaryDetails(long diaryId) {
        return null;
    }

    @Override
    public void deleteDiary(long diaryId) {

    }

    @Override
    public List<TeamDiariesResponseDTO> requestAllTeamDiaries(long userId) {
        return null;
    }

    @Override
    public List<DiaryIdResponseDTO> requestDiaryId(String diaryTitle, String writtenDate, long writerId) {
        return null;
    }
}
