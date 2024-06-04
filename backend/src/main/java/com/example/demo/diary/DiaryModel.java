package com.example.demo.diary;

public class DiaryModel {

    // 공유할 그룹
    int id;
    String written_date;
    int writer_id;
    String diary_title;
    String details;

    public DiaryModel() {
    }

    public DiaryModel(int id, String written_date, int writer_id, String diary_title, String details) {
        this.id = id;
        this.written_date = written_date;
        this.writer_id = writer_id;
        this.diary_title = diary_title;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getWritten_date() {
        return written_date;
    }

    public int getWriter_id() {
        return writer_id;
    }

    public String getDiary_title() {
        return diary_title;
    }

    public String getDetails() {
        return details;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWritten_date(String written_date) {
        this.written_date = written_date;
    }

    public void setWriter_id(int writer_id) {
        this.writer_id = writer_id;
    }

    public void setDiary_title(String diary_title) {
        this.diary_title = diary_title;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
