package com.example.demo.dto;

import lombok.Getter;

@Getter
public class MemberInviteRequestDTO {
    private int id;
    private int userId;
    private int teamId;
    private int status;
    private int inviterId;    // 초대를 한 사람
}
