package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TeamEntity {
    @Id
    int id;
    String team_name;
    int creator_id;

    public TeamEntity() {
    }

    public TeamEntity(int id, String team_name, int creator_id) {
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
