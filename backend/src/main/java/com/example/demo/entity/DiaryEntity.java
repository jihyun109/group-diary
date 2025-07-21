package com.example.demo.entity;

import com.example.demo.model.TeamModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEntity {
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
