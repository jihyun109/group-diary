package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String initial;
    private String color;
    private String password;
    private String email;
}
