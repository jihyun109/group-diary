package com.example.demo.response;


public class TeamMembersNameResponse {
    int user_id;
    String name;    // userName

    String color;

    public TeamMembersNameResponse() {
    }

    public TeamMembersNameResponse(int user_id, String name, String color) {
        this.user_id = user_id;
        this.name = name;
        this.color = color;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
