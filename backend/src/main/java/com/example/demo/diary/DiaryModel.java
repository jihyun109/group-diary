package com.example.demo.diary;

import com.example.demo.team.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class DiaryModel {

    // 공유할 그룹
    int id;
    String written_date;
    int writer_id;
    String diary_title;
    String details;

    String first_name;
    String last_name;

    List<TeamModel> sharedTeamList=new ArrayList<>();

    public DiaryModel() {
    }

    public DiaryModel(int id, String written_date, int writer_id, String diary_title, String details, String first_name, String last_name, List<TeamModel> sharedTeamList) {
        this.id = id;
        this.written_date = written_date;
        this.writer_id = writer_id;
        this.diary_title = diary_title;
        this.details = details;
        this.first_name = first_name;
        this.last_name = last_name;
        this.sharedTeamList = sharedTeamList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWritten_date() {
        return written_date;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

    public int getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(int writer_id) {
        this.writer_id = writer_id;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<TeamModel> getSharedTeamList() {
        return sharedTeamList;
    }

    public void setSharedTeamList(List<TeamModel> sharedTeamList) {
        this.sharedTeamList = sharedTeamList;
    }
}
