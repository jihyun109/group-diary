package com.example.demo.diary;

import com.example.demo.dto.DiaryEditRequestDTO;
import com.example.demo.dto.DiaryWriteRequestDTO;
import com.example.demo.response.AllTeamDiariesResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DiaryMapper {
    List<DiaryModel> selectDiaries();   // 모든 일기 정보 조회

    void insertDiary(DiaryWriteRequestDTO diary); // 일기 생성

    void updateDiary(DiaryEditRequestDTO diary); // 일기 수정

    void deleteDiary(int diaryId);  // 일기 삭제

    List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId); // 사용자가 속해있는 모든 그룹의 일기 조회

    DiaryModel requestDiaryDetails(long diaryId);    // 일기 상세 내용 요청

    List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId);    // 일기 아이디 요청
}
