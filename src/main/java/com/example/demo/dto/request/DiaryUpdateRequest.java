package com.example.demo.dto.request;

import lombok.Setter;

@Setter
public class DiaryUpdateRequest {
    private Long id;
    private String written_date;
    private Long writer_id;
    private String diary_title;
    private String details;
    private String first_name;
    private String last_name;
}
