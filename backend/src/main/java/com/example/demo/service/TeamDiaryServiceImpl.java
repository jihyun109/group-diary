package com.example.demo.service;

import com.example.demo.dto.TeamDiaryPostRequestDTO;
import com.example.demo.repository.inter.TeamDiaryRepository;
import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamDiaryServiceImpl implements TeamDiaryService {
    private final TeamDiaryRepository teamDiaryRepository;

    // 팁 일기 생성
    @Override
    public void insertTeamDiary(TeamDiaryPostRequestDTO teamDiary) {
        teamDiaryRepository.insertTeamDiary(teamDiary);
    }

    // 팀 일기 삭제 (공유 해제)
    @Override
    public void deleteTeamDiary(long diaryId, long teamId) {
        teamDiaryRepository.deleteTeamDiary(diaryId, teamId);
    }

    // 현재 팀에 공유된 일기 리스트 요청
    @Override
    public List<TeamDiaryListResponse> requestTeamDiaryList(long teamId) {
        return teamDiaryRepository.requestTeamDiaryList(teamId);
    }

    // 선택한 일기가 공유된 팀들의 id, 이름 요청
    @Override
    public List<SharedTeamsResponse> requestSharedTeams(long diaryId) {
        return teamDiaryRepository.requestSharedTeams(diaryId);
    }
}
