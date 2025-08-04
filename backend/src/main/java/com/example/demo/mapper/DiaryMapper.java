package com.example.demo.mapper;

import com.example.demo.dto.DiaryEditRequestDTOv2;
import com.example.demo.model.Diary;
import com.example.demo.model.DiaryModel;
import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.dto.TeamDiariesResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiaryMapper {
    void insertDiary(DiaryWriteRequestDTO diary); // 일기 생성

    void createDiary(Diary diary);

    void updateDiary(DiaryEditRequestDTO diary); // 일기 수정

    void updateDiaryV2(DiaryEditRequestDTOv2 diary);

    void deleteDiary(long diaryId);  // 일기 삭제

    List<TeamDiariesResponseDTO> requestAllTeamDiaries(long userId); // 사용자가 속해있는 모든 그룹의 일기 조회

    DiaryModel requestDiaryDetails(long diaryId);    // 일기 상세 내용 요청

    List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, long writerId);    // 일기 아이디 요청
}
