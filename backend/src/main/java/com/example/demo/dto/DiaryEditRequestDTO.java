package com.example.demo.dto;

import com.example.demo.model.TeamModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiaryEditRequestDTO {
    private long id;
    private String writtenDate;
    private long writerId;
    private String diaryTitle;
    private String details;

    private String firstName;
    private String lastName;
    private List<TeamModel> sharedTeamList = new ArrayList<>();
}
