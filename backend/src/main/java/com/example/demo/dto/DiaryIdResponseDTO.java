package com.example.demo.dto;

import com.example.demo.model.TeamModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DiaryIdResponseDTO {
    private long id;
    private String writtenDate;
    private long writerId;
    private String diaryTitle;
    private String details;

    private String firstName;
    private String lastName;

    private List<TeamModel> sharedTeamList;
}
