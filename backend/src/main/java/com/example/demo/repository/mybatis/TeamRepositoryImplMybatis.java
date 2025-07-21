package com.example.demo.repository.mybatis;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.dto.TeamSearchIdResponseDTO;
import com.example.demo.mapper.TeamMapper;
import com.example.demo.model.TeamModel;
import com.example.demo.repository.inter.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeamRepositoryImplMybatis implements TeamRepository {
    private final TeamMapper teamMapper;

    @Override
    public List<TeamModel> searchTeamByDiaryId(long diaryId) {
        return teamMapper.searchTeamByDiaryId(diaryId);
    }

    @Override
    public void insertTeam(TeamCreateRequestDTO team) {
        teamMapper.insertTeam(team);
    }

    @Override
    public List<TeamSearchIdResponseDTO> findTeamId(String teamName, long creatorId) {
        List<TeamModel> teamModelList = teamMapper.findTeamId(teamName, creatorId);

        return teamModelList.stream()
                .map(teamModel -> TeamSearchIdResponseDTO.builder()
                        .id(teamModel.getId())
                        .team_name(teamModel.getTeam_name())
                        .creator_id(teamModel.getCreator_id())
                        .build())
                .collect(Collectors.toList());
    }
}
