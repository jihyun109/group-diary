package com.example.demo.service;

import com.example.demo.dto.MemberInviteInTeamRequestDTO;
import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;

public interface MemberService {
    void insertMember(MemberInviteRequestDTO member);
    void inviteInTeam(MemberInviteInTeamRequestDTO memberInviteInTeamRequestDTO);
    void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO);
    void deleteMember(long id);
}
