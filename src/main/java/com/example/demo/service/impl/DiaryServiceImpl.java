package com.example.demo.service.impl;

import com.example.demo.dto.request.DiaryCreateRequest;
import com.example.demo.dto.request.DiaryUpdateRequest;
import com.example.demo.dto.response.AllTeamDiariesResponse;
import com.example.demo.dto.response.DiaryDetailResponse;
import com.example.demo.dto.response.DiaryIdResponse;
import com.example.demo.dto.response.DiaryListResponse;
import com.example.demo.entity.DiaryEntity;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.service.inter.DiaryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    //    private final TeamEntity teamEntity;
    private ModelMapper modelMapper;

    // 모든 일기 리스트 조회
    @Override
    public List<DiaryListResponse> getDiaries() {
        return diaryRepository.findAll().stream()
                .map(diary -> modelMapper.map(diary, DiaryListResponse.class))
                .collect(Collectors.toList());
    }

    // 일기 생성
    @Override
    public void insertDiary(DiaryCreateRequest diaryCreateRequest) {
        DiaryEntity diaryEntity = modelMapper.map(diaryCreateRequest, DiaryEntity.class);
        diaryRepository.save(diaryEntity);
    }

    // 일기 수정
    @Override
    public void updateDiary(Long diaryId, DiaryUpdateRequest diaryUpdateRequest) {
        diaryUpdateRequest.setId(diaryId);
        DiaryEntity diaryEntity = modelMapper.map(diaryUpdateRequest, DiaryEntity.class);
        diaryRepository.save(diaryEntity);
    }

    // 일기 삭제
    @Override
    public void deleteDiary(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

    // 사용자가 포함된 모든 그룹의 일기 정보 요청
    @Override
    public List<AllTeamDiariesResponse> requestAllTeamDiaries(Long userId) {
        return diaryRepository.getAllTeamDiaries(userId).stream()
                .map(diary -> modelMapper.map(diary, AllTeamDiariesResponse.class))
                .collect(Collectors.toList());
    }

    // 선택한 다이어리 상세 정보 요청
    @Override
    public DiaryDetailResponse requestDiaryDetails(Long diaryId) {
        return modelMapper.map(diaryRepository.findById(diaryId), DiaryDetailResponse.class);
    }

    // 일기 id 요청
    @Override
    public Long requestDiaryId(String diaryTitle, String writtenDate, Long writerId) {
        return diaryRepository.findDiaryId(diaryTitle, writtenDate, writerId);
    }
}
