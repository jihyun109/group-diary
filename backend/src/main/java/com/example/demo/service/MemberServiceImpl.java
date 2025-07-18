package com.example.demo.service;

import com.example.demo.dto.MemberInviteInTeamRequestDTO;
import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;
import com.example.demo.member.MemberMapper;
import com.example.demo.member.MemberModel;
import com.example.demo.repository.MemberRepository;
import com.example.demo.request.TeamRequest;
import com.example.demo.response.InvitedListResponse;
import com.example.demo.response.TeamMembersNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    // 멤버 생성 (초대)
    @Override
    public void insertMember(MemberInviteRequestDTO member) {
        memberRepository.inviteMember(member);
    }

    // 팀에 멤버 초대
    @Override
    public void inviteInTeam(MemberInviteInTeamRequestDTO memberInviteInTeamRequestDTO) {
        memberRepository.inviteInTeam(memberInviteInTeamRequestDTO);
    }

    // 멤버 정보 수정 (초대 수락/거절)
    @Override
    public void updateMember(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        memberRepository.updateMember(memberUpdateRequestDTO);
    }

    @Override
    public void deleteMember(long id) {
        memberRepository.deleteMember(id);

    }

//    // 모든 팀의 멤버 리스트 조회
//    public List<MemberModel> getMembers() {
//        return memberMapper.selectMembers();
//    }

    // 팀의 모든 멤버 이름 요청
    public List<TeamMembersNameResponse> requestTeamMembersName(int teamId) {
        return memberMapper.requestTeamMembersName(teamId);
    }

    // 사용자가 초대된 팀 리스트 요청
    public List<InvitedListResponse> requestInvitedList(int userId) {
        return memberMapper.requestInvitedList(userId);
    }

    // 사용자가 멤버인 팀 리스트 요청
    public List<TeamRequest> requestUserTeamList(int userId) {
        return memberMapper.requestUserTeamList(userId);
    }

}
