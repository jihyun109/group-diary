package com.example.demo.repository.mybatisImpl;

import com.example.demo.dto.*;
import com.example.demo.mapper.DiaryMapper;
import com.example.demo.model.Diary;
import com.example.demo.model.DiaryModel;
import com.example.demo.repository.inter.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
@RequiredArgsConstructor
public class DiaryRepositoryImplMybatis implements DiaryRepository {
    private final DiaryMapper diaryMapper;

    @Override
    public void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO) {
        diaryMapper.insertDiary(diaryWriteRequestDTO);
    }

    @Override
    public long createDiary(DiaryWriteRequestDTOv2 diaryWriteRequestDTOv2) {
        Diary diary = diaryWriteRequestDTOv2ToModel(diaryWriteRequestDTOv2);
        diaryMapper.createDiary(diary);

        return diary.getId();
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

    private Diary diaryWriteRequestDTOv2ToModel(DiaryWriteRequestDTOv2 dto) {
        return Diary.builder()
                .writerId(dto.getWriterId())
                .diaryTitle(dto.getTitle())
                .details(dto.getDetails())
                .writtenDate(dto.getWrittenDate())
                .build();
    }
}
