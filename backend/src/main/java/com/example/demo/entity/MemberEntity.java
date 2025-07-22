package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    private long id;
    private long user_id;
    private long team_id;
    private long status;
    private long inviter_id;    // 초대를 한 사람
}
