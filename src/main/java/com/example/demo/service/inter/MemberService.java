package com.example.demo.service.inter;

import com.example.demo.dto.request.MemberInviteRequest;
import com.example.demo.dto.request.MemberUpdateRequest;
import com.example.demo.dto.response.InvitedListResponse;
import com.example.demo.dto.response.MemberListResponse;
import com.example.demo.dto.request.TeamRequest;

import java.util.List;

public interface MemberService {
    List<MemberListResponse> getMembers();

    void inviteMember(MemberInviteRequest member);

    void updateMember(long memberId, MemberUpdateRequest member);

    void deleteMember(long memberId);

    List<String> requestTeamMembersName(long teamId);

    List<InvitedListResponse> requestInvitedList(long userId);

    List<TeamRequest> requestUserTeamList(long userId);
}
