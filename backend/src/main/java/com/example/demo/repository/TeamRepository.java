package com.example.demo.repository;

import com.example.demo.model.TeamModel;

import java.util.List;

public interface TeamRepository {
    List<TeamModel> searchTeamByDiaryId(long diaryId);
}
