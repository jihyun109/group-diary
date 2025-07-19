package com.example.demo.repository;

import com.example.demo.mapper.DiaryMapper;
import com.example.demo.model.DiaryModel;
import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.response.AllTeamDiariesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DiaryRepositoryImplMybatis implements DiaryRepository {
    private final DiaryMapper diaryMapper;

    @Override
    public void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO) {
        diaryMapper.insertDiary(diaryWriteRequestDTO);
    }

    @Override
    public void updateDiary(DiaryEditRequestDTO diaryEditRequestDTO) {
        diaryMapper.updateDiary(diaryEditRequestDTO);
    }

    @Override
    public DiaryDetailResponseDTO requestDiaryDetails(long diaryId) {
        DiaryModel diaryModel = diaryMapper.requestDiaryDetails(diaryId);
        return DiaryDetailResponseDTO.from(diaryModel);
    }

    @Override
    public void deleteDiary(int diaryId) {
        diaryMapper.deleteDiary(diaryId);
    }

    @Override
    public List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId) {
        return diaryMapper.requestAllTeamDiaries(userId);
    }

    @Override
    public List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId) {
        return diaryMapper.requestDiaryId(diaryTitle, writtenDate, writerId);
    }
}
