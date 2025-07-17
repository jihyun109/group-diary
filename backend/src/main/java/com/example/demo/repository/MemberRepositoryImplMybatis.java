package com.example.demo.repository;

import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRepositoryImplMybatis implements MemberRepository {
    private final MemberMapper memberMapper;
    @Override
    public void inviteMember(MemberInviteRequestDTO memberInviteRequestDTO) {
        memberMapper.insertMember(memberInviteRequestDTO);
    }
}
