package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    private long id;
    private String writtenDate;
    private long writerId;
    private String diaryTitle;
    private String details;
}
