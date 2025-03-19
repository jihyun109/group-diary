package com.example.demo.dto.response;

import com.example.demo.entity.MemberEntity;
import com.example.demo.entity.TeamEntity;

public class TeamRequest {

    private MemberEntity memberEntity;
    private TeamEntity teamEntity;

    public TeamRequest(MemberEntity memberEntity, TeamEntity teamEntity) {
        this.memberEntity = memberEntity;
        this.teamEntity = teamEntity;
    }

    public MemberEntity getMemberModel() {
        return memberEntity;
    }

    public TeamEntity getTeamModel() {
        return teamEntity;
    }

    public void setMemberModel(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    public void setTeamModel(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }
}
