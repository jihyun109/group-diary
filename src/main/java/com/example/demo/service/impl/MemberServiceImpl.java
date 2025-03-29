package com.example.demo.service.impl;

import com.example.demo.dto.request.MemberInviteRequest;
import com.example.demo.dto.request.MemberUpdateRequest;
import com.example.demo.dto.request.TeamRequest;
import com.example.demo.dto.response.InvitedListResponse;
import com.example.demo.dto.response.MemberListResponse;
import com.example.demo.entity.GroupMemberEntity;
import com.example.demo.enums.MembershipStatus;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.inter.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private ModelMapper modelMapper;

    // 모든 팀의 멤버 리스트 조회
    @Override
    public List<MemberListResponse> getMembers() {
        return memberRepository.findAll().stream()
                .map(member -> modelMapper.map(member, MemberListResponse.class))
                .collect(Collectors.toList());
    }

    // 멤버 생성(초대)
    @Override
    public void inviteMember(MemberInviteRequest memberInviteRequest) {
        memberInviteRequest.setStatus(MembershipStatus.INVITED);
        GroupMemberEntity groupMemberEntity = modelMapper.map(memberInviteRequest, GroupMemberEntity.class);
        memberRepository.save(groupMemberEntity);
    }

    // 멤버 수정
    @Override
    public void updateMember(long memberId, MemberUpdateRequest member) {
        member.setId(memberId);
        GroupMemberEntity groupMemberEntity = modelMapper.map(member, GroupMemberEntity.class);
        memberRepository.save(groupMemberEntity);
    }

    // 멤버 삭제
    @Override
    public void deleteMember(long memberId) {
        memberRepository.deleteById(memberId);
    }

    // 팀의 모든 멤버 이름 요청
    @Override
    public List<String> requestTeamMembersName(long teamId) {
        return memberRepository.findMembersByTeamId(teamId);
    }

    // 사용자가 초대된 팀 리스트 요청
    @Override
    public List<InvitedListResponse> requestInvitedList(long userId) {
        return memberRepository.requestInvitedList(userId);
    }

    // 사용자가 멤버인 팀 리스트 요청
    @Override
    public List<TeamRequest> requestUserTeamList(long userId) {
        return memberRepository.requestUserTeamList(userId);
    }
}
