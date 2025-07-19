package com.example.demo.repository;

import com.example.demo.diary.DiaryMapper;
import com.example.demo.diary.DiaryModel;
import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
