package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvitedListResponse {
    private long id;
    private long team_id;
    private String team_name;
    private long inviter_id;
    private String first_name;    // userName
    private long status;
}
