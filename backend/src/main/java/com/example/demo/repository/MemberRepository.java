package com.example.demo.repository;


import com.example.demo.dto.MemberInviteRequestDTO;

public interface MemberRepository {
    void inviteMember(MemberInviteRequestDTO memberInviteRequestDTO);
}
