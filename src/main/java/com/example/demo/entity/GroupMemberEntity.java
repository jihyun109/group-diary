package com.example.demo.entity;

import com.example.demo.enums.MembershipStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class GroupMemberEntity {
    @Id
    long id;
    @ManyToOne
    UserEntity user;    // 초대받은 사용자
    @ManyToOne
    TeamEntity team;
    MembershipStatus status;
    @ManyToOne
    @JoinColumn(name = "inviter_id")
    private UserEntity inviter; // 초대한 사용자
}