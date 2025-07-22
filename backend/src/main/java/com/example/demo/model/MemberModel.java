package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberModel {
    private long id;
    private long user_id;
    private long team_id;
    private long status;
    private long inviter_id;    // 초대를 한 사람
}
