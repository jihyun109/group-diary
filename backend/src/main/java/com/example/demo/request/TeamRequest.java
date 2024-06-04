package com.example.demo.request;

import com.example.demo.member.MemberModel;
import com.example.demo.team.TeamModel;

public class TeamRequest {

    private MemberModel memberModel;
    private TeamModel teamModel;

    public TeamRequest(MemberModel memberModel, TeamModel teamModel) {
        this.memberModel = memberModel;
        this.teamModel = teamModel;
    }

    public MemberModel getMemberModel() {
        return memberModel;
    }

    public TeamModel getTeamModel() {
        return teamModel;
    }

    public void setMemberModel(MemberModel memberModel) {
        this.memberModel = memberModel;
    }

    public void setTeamModel(TeamModel teamModel) {
        this.teamModel = teamModel;
    }
}
