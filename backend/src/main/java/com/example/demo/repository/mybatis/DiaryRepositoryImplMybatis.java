package com.example.demo.repository.mybatis;

import com.example.demo.dto.*;
import com.example.demo.mapper.DiaryMapper;
import com.example.demo.model.DiaryModel;
import com.example.demo.repository.inter.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public void deleteDiary(long diaryId) {
        diaryMapper.deleteDiary(diaryId);
    }

    @Override
    public List<TeamDiariesResponseDTO> requestAllTeamDiaries(long userId) {
        return diaryMapper.requestAllTeamDiaries(userId);
    }

    @Override
    public List<DiaryIdResponseDTO> requestDiaryId(String diaryTitle, String writtenDate, long writerId) {
        List<DiaryModel> diaryModelList = diaryMapper.requestDiaryId(diaryTitle, writtenDate, writerId);

        return diaryModelList.stream()
                .map(model -> DiaryIdResponseDTO.builder()
                        .id(model.getId())
                        .writtenDate(model.getWrittenDate())
                        .writerId(model.getWriterId())
                        .diaryTitle(model.getDiaryTitle())
                        .details(model.getDetails())
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .sharedTeamList(model.getSharedTeamList())
                        .build())
                .collect(Collectors.toList());
    }
}
