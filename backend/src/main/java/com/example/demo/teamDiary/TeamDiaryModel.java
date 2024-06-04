package com.example.demo.teamDiary;

public class TeamDiaryModel {
    int id;
    int diary_id;
    int team_id;

    public TeamDiaryModel() {
    }

    public TeamDiaryModel(int id, int diary_id, int team_id) {
        this.id = id;
        this.diary_id = diary_id;
        this.team_id = team_id;
    }

    public int getId() {
        return id;
    }

    public int getDiary_id() {
        return diary_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiary_id(int diary_id) {
        this.diary_id = diary_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }
}
