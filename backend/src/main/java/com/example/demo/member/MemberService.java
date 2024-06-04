package com.example.demo.member;

import com.example.demo.request.TeamRequest;
import com.example.demo.response.InvitedListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    // 모든 팀의 멤버 리스트 조회
    public List<MemberModel> getMembers() {
        return memberMapper.selectMembers();
    }

    // 멤버 생성 (초대)
    public void insertMember(MemberModel member) {
        memberMapper.insertMember(member);
    }

    // 멤버 수정
    public void updateMember(int memberId, MemberModel member) {
        member.setId(memberId);
        memberMapper.updateMember(member);
    }

    // 멤버 삭제
    public void deleteMember(int memberId) {
        memberMapper.deleteMember(memberId);
    }

    // 팀의 모든 멤버 이름 요청
    public List<String> requestTeamMembersName(int teamId) {
        return memberMapper.requestTeamMembersName(teamId);
    }

    // 팀에 멤버를 초대
    public void requestInviteMember(int teamId, MemberModel member) {
        member.setId(teamId);
        memberMapper.requestInviteMember(member);
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
