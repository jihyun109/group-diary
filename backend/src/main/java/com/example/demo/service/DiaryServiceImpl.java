package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.repository.inter.DiaryRepository;
import com.example.demo.repository.inter.TeamDiaryRepository;
import com.example.demo.repository.inter.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final TeamRepository teamRepository;
    private final TeamDiaryRepository teamDiaryRepository;

    // 일기 생성(일기 작성)
    @Override
    public void insertDiary(DiaryWriteRequestDTO diaryWriteRequestDTO) {
        diaryRepository.insertDiary(diaryWriteRequestDTO);
    }

    @Override
    public void writeDiary(DiaryWriteRequestDTOv2 diaryWriteRequestDTOv2) {
        long diaryId = diaryRepository.createDiary(diaryWriteRequestDTOv2);

        // 선택된 팀에 일기 공유
        teamDiaryRepository.insertTeamDiaryV2(diaryId, diaryWriteRequestDTOv2.getSharedTeamList());
    }

    // 일기 수정
    @Override
    public void updateDiary(long id, DiaryEditRequestDTO diaryData) {
        diaryData.setId(id);
        diaryRepository.updateDiary(diaryData);
    }

    @Override
    public void updateDiary(DiaryEditRequestDTOv2 diaryEditRequestDTOv2) {
        diaryRepository.updateDiaryV2(diaryEditRequestDTOv2);

        Long diaryId = diaryEditRequestDTOv2.getId();
        teamDiaryRepository.insertTeamDiaryV2(diaryId, diaryEditRequestDTOv2.getAddedTeamIds());
        teamDiaryRepository.deleteTeamDiaryV2(diaryId, diaryEditRequestDTOv2.getRemovedTeamIds());
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
    public void deleteDiary(long diaryId) {
        diaryRepository.deleteDiary(diaryId);
    }

    // 사용자가 포함된 모든 그룹의 일기 정보 요청
    @Override
    public List<TeamDiariesResponseDTO> requestAllTeamDiaries(long userId) {
        return diaryRepository.requestAllTeamDiaries(userId);
    }

    // 일기 id 요청
    @Override
    public List<DiaryIdResponseDTO> requestDiaryId(String diaryTitle, String writtenDate, long writerId) {
        return diaryRepository.requestDiaryId(diaryTitle, writtenDate, writerId);
    }
}
