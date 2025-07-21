package com.example.demo.service;

import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;
import com.example.demo.request.TeamRequest;
import com.example.demo.response.InvitedListResponse;
import com.example.demo.response.TeamMembersNameResponse;

import java.util.List;

public interface MemberService {
    void insertMember(MemberInviteRequestDTO member);

    void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO);

    void deleteMember(long id);

    List<TeamMembersNameResponse> requestTeamMembersName(long teamId);

    List<InvitedListResponse> requestInvitedList(long userId);

    List<TeamRequest> requestUserTeamList(long userId);
}
