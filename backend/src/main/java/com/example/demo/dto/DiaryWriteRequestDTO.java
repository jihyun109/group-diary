package com.example.demo.dto;

import com.example.demo.diary.DiaryModel;
import com.example.demo.team.TeamModel;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class DiaryWriteRequestDTO {
    private int id;
    private String writtenDate;
    private int writerId;
    private String diaryTitle;
    private String details;

    private String firstName;
    private String lastName;
    private List<TeamModel> sharedTeamList = new ArrayList<>();

    public static DiaryWriteRequestDTO from(DiaryModel diaryModel) {
        return DiaryWriteRequestDTO.builder()
                .id(diaryModel.getId())
                .writtenDate(diaryModel.getWritten_date())
                .writerId(diaryModel.getWriter_id())
                .diaryTitle(diaryModel.getDiary_title())
                .details(diaryModel.getDetails())
                .firstName(diaryModel.getFirst_name())
                .lastName(diaryModel.getLast_name())
                .sharedTeamList(diaryModel.getSharedTeamList())
                .build();
    }
}
