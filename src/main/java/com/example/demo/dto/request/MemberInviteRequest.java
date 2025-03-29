package com.example.demo.dto.request;

import com.example.demo.enums.MembershipStatus;
import lombok.Setter;

@Setter
public class MemberInviteRequest {
    long id;
    int userId;
    int teamId;
    MembershipStatus status;
    int inviterId;    // 초대를 한 사람
}
