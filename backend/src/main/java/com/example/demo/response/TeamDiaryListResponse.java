package com.example.demo.response;


public class TeamDiaryListResponse {
    //u.id, u.name, u.initial, u.color, d.written_date, d.title,

    int id; // user
    String first_name;    // user

    String last_name;
    String initial;
    String color;
    String written_date;
    String diary_title;

    public TeamDiaryListResponse() {
    }

    public TeamDiaryListResponse(int id, String first_name, String last_name, String initial, String color, String written_date, String diary_title) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.initial = initial;
        this.color = color;
        this.written_date = written_date;
        this.diary_title = diary_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWritten_date() {
        return written_date;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }
}
