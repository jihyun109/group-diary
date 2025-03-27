package com.example.demo.service.inter;

import com.example.demo.dto.response.InvitedListResponse;
import com.example.demo.dto.response.TeamMembersNameResponse;
import com.example.demo.dto.request.TeamRequest;
import com.example.demo.entity.MemberModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    List<MemberModel> getMembers();

    void insertMember(MemberModel member);

    void updateMember(int memberId, MemberModel member);

    void deleteMember(int memberId);

    List<TeamMembersNameResponse> requestTeamMembersName(int teamId);

    void requestInviteMember(int teamId, MemberModel member);

    List<InvitedListResponse> requestInvitedList(int userId);

    List<TeamRequest> requestUserTeamList(int userId);
}
