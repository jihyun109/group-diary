package com.example.demo.response;


public class TeamMembersNameResponse {
    int user_id;
    String name;    // userName

    public TeamMembersNameResponse() {
    }

    public TeamMembersNameResponse(int user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
