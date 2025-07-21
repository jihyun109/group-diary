package com.example.demo.repository.jpaImpl;

import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;
import com.example.demo.repository.inter.MemberRepository;
import com.example.demo.request.TeamRequest;
import com.example.demo.response.InvitedListResponse;
import com.example.demo.response.TeamMembersNameResponse;

import java.util.List;

public class MemberRepositoryImplJpa implements MemberRepository {
    @Override
    public void inviteMember(MemberInviteRequestDTO memberInviteRequestDTO) {

    }

    @Override
    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {

    }

    @Override
    public void deleteMember(long id) {

    }

    @Override
    public List<TeamMembersNameResponse> requestTeamMembersName(long teamId) {
        return null;
    }

    @Override
    public List<InvitedListResponse> requestInvitedList(long userId) {
        return null;
    }

    @Override
    public List<TeamRequest> requestUserTeamList(long userId) {
        return null;
    }
}
