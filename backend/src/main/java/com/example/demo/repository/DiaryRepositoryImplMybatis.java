package com.example.demo.repository;

import com.example.demo.diary.DiaryMapper;
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
}
