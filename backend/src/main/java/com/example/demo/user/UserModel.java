package com.example.demo.user;

public class UserModel {
    int id;
    String firstName;
    String lastName;
    String initial;
    String color;
    String password;
    String email;

    public UserModel() {
    }

    public UserModel(int id, String firstName, String lastName, String initial, String color, String password, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.initial = initial;
        this.color = color;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
