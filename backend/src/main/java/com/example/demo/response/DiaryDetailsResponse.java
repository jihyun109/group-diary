package com.example.demo.response;


public class DiaryDetailsResponse {
    String diary_title;
    String written_date;
    String details;
    int team_id;
    String team_name;

    public DiaryDetailsResponse() {
    }

    public DiaryDetailsResponse(String diary_title, String written_date, String details, int team_id, String team_name) {
        this.diary_title = diary_title;
        this.written_date = written_date;
        this.details = details;
        this.team_id = team_id;
        this.team_name = team_name;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public String getWritten_date() {
        return written_date;
    }

    public String getDetails() {
        return details;
    }

    public int getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }
}
