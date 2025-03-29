package com.example.demo.dto.request;

import lombok.Setter;

@Setter
public class MemberUpdateRequest {
    long id;
    int userId;
    int teamId;
    int status;
    int inviterId;    // 초대를 한 사람
}
