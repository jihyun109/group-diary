package com.example.demo.response;


public class TeamDiaryListResponse {
    //u.id, u.name, u.initial, u.color, d.written_date, d.title,

    int id; // user
    String name;    // user
    String initial;
    String color;
    String written_date;
    String diary_title;

    public TeamDiaryListResponse() {
    }

    public TeamDiaryListResponse(int id, String name, String initial, String color, String written_date, String diary_title) {
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.color = color;
        this.written_date = written_date;
        this.diary_title = diary_title;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInitial() {
        return initial;
    }

    public String getColor() {
        return color;
    }

    public String getWritten_date() {
        return written_date;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }
}
