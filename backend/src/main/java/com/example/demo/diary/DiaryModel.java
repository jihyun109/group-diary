package com.example.demo.diary;

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
    private String written_date;
    private int writer_id;
    private String diary_title;
    private String details;

    private String first_name;
    private String last_name;

    private List<TeamModel> sharedTeamList = new ArrayList<>();

}
