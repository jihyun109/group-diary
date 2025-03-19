package com.example.demo.dto.request;


public class DiaryDetailsResponse {
    int id;
    String diary_title;
    String written_date;
    String details;
    int team_id;
    String team_name;

    String first_name;
    String last_name;

    public DiaryDetailsResponse() {
    }

    public DiaryDetailsResponse(int id, String diary_title, String written_date, String details, int team_id, String team_name, String first_name, String last_name) {
        this.id = id;
        this.diary_title = diary_title;
        this.written_date = written_date;
        this.details = details;
        this.team_id = team_id;
        this.team_name = team_name;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public String getWritten_date() {
        return written_date;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
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
}
