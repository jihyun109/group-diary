package com.example.demo.repository;

import com.example.demo.dto.DiaryWriteRequestDTO;

public interface DiaryRepository {
    void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO);
}
