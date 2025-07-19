package com.example.demo.dto;

import com.example.demo.model.DiaryModel;
import com.example.demo.model.TeamModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DiaryDetailResponseDTO {
    private long id;
    private String writtenDate;
    private int writerId;
    private String diaryTitle;
    private String details;

    private String firstName;
    private String lastName;
    private List<TeamModel> sharedTeamList;

    public static DiaryDetailResponseDTO from (DiaryModel diaryModel) {
        return DiaryDetailResponseDTO.builder()
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
