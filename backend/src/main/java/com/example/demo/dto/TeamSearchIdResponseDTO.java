package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamSearchIdResponseDTO {
    private long id;
    private String team_name;
    private long creator_id;
}
