package com.example.demo.entity;

import com.example.demo.model.TeamModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DiaryEntity {
    // 공유할 그룹
    @Id
    private long id;
    private String writtenDate;
    private long writerId;
    private String diaryTitle;
    private String details;
}
