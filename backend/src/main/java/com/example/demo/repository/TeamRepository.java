package com.example.demo.repository;

import com.example.demo.model.TeamModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeamRepository {
    List<TeamModel> searchTeamByDiaryId(long diaryId);
    void insertTeam(TeamModel team);
    List<TeamModel> findTeamId(String teamName, long creatorId);
}
