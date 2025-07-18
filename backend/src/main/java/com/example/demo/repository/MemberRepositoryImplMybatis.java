package com.example.demo.repository;

import com.example.demo.dto.MemberInviteInTeamRequestDTO;
import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;
import com.example.demo.member.MemberMapper;
import com.example.demo.response.TeamMembersNameResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberRepositoryImplMybatis implements MemberRepository {
    private final MemberMapper memberMapper;

    @Override
    public void inviteMember(MemberInviteRequestDTO memberInviteRequestDTO) {
        memberMapper.insertMember(memberInviteRequestDTO);
    }

    @Override
    public void inviteInTeam(MemberInviteInTeamRequestDTO memberInviteInTeamRequestDTO) {
        memberMapper.requestInviteMember(memberInviteInTeamRequestDTO);
    }

    @Override
    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        memberMapper.updateMember(memberUpdateRequestDTO);
    }

    @Override
    public void deleteMember(long id) {
        memberMapper.deleteMember(id);
    }

    @Override
    public List<TeamMembersNameResponse> requestTeamMembersName(long teamId) {
        return memberMapper.requestTeamMembersName(teamId);
    }
}
