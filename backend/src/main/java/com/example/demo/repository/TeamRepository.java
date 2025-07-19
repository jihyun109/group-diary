package com.example.demo.repository;

import com.example.demo.team.TeamModel;

import java.util.List;

public interface TeamRepository {
    List<TeamModel> searchTeamByDiaryId(long diaryId);
}
