package com.example.demo.dto;

import com.example.demo.model.TeamModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamResponseDTO {
    private long id;
    private String teamName;
    private long creatorId;

    public static TeamResponseDTO from(TeamModel teamModel) {
        return TeamResponseDTO.builder()
                .id(teamModel.getId())
                .teamName(teamModel.getTeam_name())
                .creatorId(teamModel.getCreator_id())
                .build();
    }
}
