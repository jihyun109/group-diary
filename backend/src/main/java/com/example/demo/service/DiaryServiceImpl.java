package com.example.demo.service;

import com.example.demo.model.DiaryModel;
import com.example.demo.dto.DiaryDetailResponseDTO;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.repository.TeamRepository;
import com.example.demo.response.AllTeamDiariesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final TeamRepository teamRepository;

    // 일기 생성(일기 작성)
    @Override
    public void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO) {
        diaryRepository.insertDiary(diaryWriteRequestDTO);
    }

    // 일기 수정
    @Override
    public void updateDiary(long id, DiaryEditRequestDTO diaryData) {
        diaryData.setId(id);
        diaryRepository.updateDiary(diaryData);
    }

    // 선택한 다이어리 상세 정보 요청
    @Override
    public DiaryDetailResponseDTO requestDiaryDetails(long diaryId) {
        DiaryDetailResponseDTO diaryDetailResponseDTO = diaryRepository.requestDiaryDetails(diaryId);
        diaryDetailResponseDTO.setSharedTeamList(teamRepository.searchTeamByDiaryId(diaryId));

        return diaryDetailResponseDTO;
    }

    // 일기 삭제
    @Override
    public void deleteDiary(int diaryId) {
        diaryRepository.deleteDiary(diaryId);
    }

    // 사용자가 포함된 모든 그룹의 일기 정보 요청
    @Override
    public List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId) {
        return diaryRepository.requestAllTeamDiaries(userId);
    }

    // 일기 id 요청
    @Override
    public List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId) {
        return diaryRepository.requestDiaryId(diaryTitle, writtenDate, writerId);
    }
}
