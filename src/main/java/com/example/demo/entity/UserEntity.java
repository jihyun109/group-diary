package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    int id;
    String first_name;
    String last_name;
    String initial;
    String color;
    String password;
    String email;

    public UserEntity() {
    }

    public UserEntity(int id, String first_name, String last_name, String initial, String color, String password, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.initial = initial;
        this.color = color;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getInitial() {
        return initial;
    }

    public String getColor() {
        return color;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
