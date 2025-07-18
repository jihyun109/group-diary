package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateRequestDTO {
    private long id;
    private long userId;
    private long teamId;
    private int status;
    private long inviterId;    // 초대를 한 사람
}
