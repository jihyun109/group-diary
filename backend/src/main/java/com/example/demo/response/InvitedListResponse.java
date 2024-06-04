package com.example.demo.response;

public class InvitedListResponse {
    // m.team_id, t.team_name, m.inviter_id, u.name, m.status
    int id;
    int team_id;
    String team_name;
    int inviter_id;
    String first_name;    // userName
    int status;

    public InvitedListResponse() {
    }

    public InvitedListResponse(int id, int team_id, String team_name, int inviter_id, String first_name, int status) {
        this.id = id;
        this.team_id = team_id;
        this.team_name = team_name;
        this.inviter_id = inviter_id;
        this.first_name = first_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getInviter_id() {
        return inviter_id;
    }

    public void setInviter_id(int inviter_id) {
        this.inviter_id = inviter_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
