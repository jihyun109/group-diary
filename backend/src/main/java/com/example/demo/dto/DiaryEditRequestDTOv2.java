package com.example.demo.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class DiaryEditRequestDTOv2 {
    private Long id;
    private String title;
    private String details;
    private String writtenDate;
    private List<Long> addedTeamIds;
    private List<Long> removedTeamIds;
}
