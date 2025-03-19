package com.example.demo.service.inter;

import com.example.demo.dto.request.InvitedListResponse;
import com.example.demo.dto.request.TeamMembersNameResponse;
import com.example.demo.dto.response.TeamRequest;
import com.example.demo.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    List<MemberEntity> getMembers();

    void insertMember(MemberEntity member);

    void updateMember(int memberId, MemberEntity member);

    void deleteMember(int memberId);

    List<TeamMembersNameResponse> requestTeamMembersName(int teamId);

    void requestInviteMember(int teamId, MemberEntity member);

    List<InvitedListResponse> requestInvitedList(int userId);

    List<TeamRequest> requestUserTeamList(int userId);
}
