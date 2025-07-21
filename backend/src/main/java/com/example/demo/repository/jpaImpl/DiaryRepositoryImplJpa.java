package com.example.demo.repository.jpaImpl;

import com.example.demo.dto.*;
import com.example.demo.repository.inter.DiaryRepository;

import java.util.List;

public class DiaryRepositoryImplJpa implements DiaryRepository {
    @Override
    public void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO) {

    }

    @Override
    public void updateDiary(DiaryEditRequestDTO diaryEditRequestDTO) {

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
