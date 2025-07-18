package com.example.demo.repository;


import com.example.demo.dto.MemberInviteInTeamRequestDTO;
import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;

public interface MemberRepository {
    void inviteMember(MemberInviteRequestDTO memberInviteRequestDTO);
    void inviteInTeam(MemberInviteInTeamRequestDTO memberInviteInTeamRequestDTO);
    void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO);
}
