package com.example.demo.model;

import lombok.Getter;

@Getter
public class Diary {
    private long id;
    private String writtenDate;
    private long writerId;
    private String diaryTitle;
    private String details;
}
