package com.example.demo.dto.request;

import com.example.demo.entity.GroupMemberEntity;
import com.example.demo.entity.TeamEntity;

public class TeamRequest {

    private GroupMemberEntity groupMemberEntity;
    private TeamEntity teamEntity;

    public TeamRequest(GroupMemberEntity groupMemberEntity, TeamEntity teamEntity) {
        this.groupMemberEntity = groupMemberEntity;
        this.teamEntity = teamEntity;
    }

    public GroupMemberEntity getMemberModel() {
        return groupMemberEntity;
    }

    public TeamEntity getTeamModel() {
        return teamEntity;
    }

    public void setMemberModel(GroupMemberEntity groupMemberEntity) {
        this.groupMemberEntity = groupMemberEntity;
    }

    public void setTeamModel(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }
}
