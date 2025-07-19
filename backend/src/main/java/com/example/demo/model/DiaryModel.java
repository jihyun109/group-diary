package com.example.demo.model;

import com.example.demo.team.TeamModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryModel {

    // 공유할 그룹
    private int id;
    private String writtenDate;
    private int writerId;
    private String diaryTitle;
    private String details;

    private String firstName;
    private String lastName;

    private List<TeamModel> sharedTeamList = new ArrayList<>();

}
