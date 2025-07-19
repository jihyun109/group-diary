package com.example.demo.model;

public class TeamModel {
    int id;
    String team_name;
    int creator_id;

    public TeamModel() {
    }

    public TeamModel(int id, String team_name, int creator_id) {
        this.id = id;
        this.team_name = team_name;
        this.creator_id = creator_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }
}
