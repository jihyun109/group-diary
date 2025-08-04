package com.example.demo.mapper;

import com.example.demo.dto.TeamDiaryPostRequestDTO;
import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeamDiaryMapper {
    // 팁 일기 생성
    void insertTeamDiary(TeamDiaryPostRequestDTO teamDiary);

    // diary 를 팀들에 공유
    void insertTeamDiaryV2(Long diaryId, List<Long> teamIds);

    // 팀 일기 삭제 (공유 해제)
    void deleteTeamDiary(long diaryId, long teamId);

    // 현재 팀에 공유된 일기 리스트 요청
    List<TeamDiaryListResponse> requestTeamDiaryList(long teamId);

    // 선택한 일기가 공유된 팀들의 id, 이름 요청
    List<SharedTeamsResponse> requestSharedTeams(long diaryId);
}
