package com.example.demo.repository;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.dto.TeamSearchIdResponseDTO;
import com.example.demo.model.TeamModel;

import java.util.List;

public interface TeamRepository {
    List<TeamModel> searchTeamByDiaryId(long diaryId);

    void insertTeam(TeamCreateRequestDTO team);

    List<TeamSearchIdResponseDTO> findTeamId(String teamName, long creatorId);
}
