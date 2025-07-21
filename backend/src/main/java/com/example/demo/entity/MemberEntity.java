package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    private int id;
    private int user_id;
    private int team_id;
    private int status;
    private int inviter_id;    // 초대를 한 사람
}
