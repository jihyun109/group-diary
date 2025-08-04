package com.example.demo.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiaryWriteRequestDTOv2 {
    private long writerId;
    private String writtenDate;
    private String title;
    private String details;
    private List<Integer> sharedTeamList = new ArrayList<>();
}
