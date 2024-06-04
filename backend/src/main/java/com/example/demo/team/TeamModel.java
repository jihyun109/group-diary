package com.example.demo.team;

public class TeamModel {
    int id;
    String team_name;

    public TeamModel(int id, String team_name) {
        this.id = id;
        this.team_name = team_name;
    }

    public TeamModel() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getId() {
        return id;
    }

    public String getTeam_name() {
        return team_name;
    }


}
