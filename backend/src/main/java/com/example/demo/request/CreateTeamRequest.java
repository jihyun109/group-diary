package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamRequest {
    String team_name;
    long user_id;
    long team_id;
    long status;
    long inviter_id;
    String member_id;
}
