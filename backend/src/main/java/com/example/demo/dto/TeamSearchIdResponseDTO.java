package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamSearchIdResponseDTO {
    private int id;
    private String team_name;
    private int creator_id;
}
