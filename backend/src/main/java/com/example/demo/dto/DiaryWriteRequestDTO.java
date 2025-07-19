package com.example.demo.dto;

import com.example.demo.model.DiaryModel;
import com.example.demo.model.TeamModel;
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
                .writtenDate(diaryModel.getWrittenDate())
                .writerId(diaryModel.getWriterId())
                .diaryTitle(diaryModel.getDiaryTitle())
                .details(diaryModel.getDetails())
                .firstName(diaryModel.getFirstName())
                .lastName(diaryModel.getLastName())
                .sharedTeamList(diaryModel.getSharedTeamList())
                .build();
    }
}
