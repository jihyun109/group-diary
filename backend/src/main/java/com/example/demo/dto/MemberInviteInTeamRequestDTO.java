package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInviteInTeamRequestDTO {
    private int id;
    private int userId;
    private int teamId;
    private int status;
    private int inviterId;    // 초대를 한 사람
}
