//package com.example.demo.legacy.mapperImpl;
//
//import com.example.demo.mapper.MemberMapper;
//import com.example.demo.entity.MemberModel;
//import com.example.demo.dto.response.TeamRequest;
//import com.example.demo.dto.request.InvitedListResponse;
//import com.example.demo.dto.request.TeamMembersNameResponse;
//import com.example.demo.service.inter.MemberService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MemberServiceMapperImpl implements MemberService {
//    private MemberMapper memberMapper;
//
//    private MemberServiceMapperImpl(MemberMapper memberMapper) {
//        this.memberMapper = memberMapper;
//    }
//
//    // 모든 팀의 멤버 리스트 조회
//    @Override
//    public List<MemberModel> getMembers() {
//        return memberMapper.selectMembers();
//    }
//
//    // 멤버 생성 (초대)
//    @Override
//    public void insertMember(MemberModel member) {
//        memberMapper.insertMember(member);
//    }
//
//    // 멤버 수정
//    @Override
//    public void updateMember(int memberId, MemberModel member) {
//        member.setId(memberId);
//        memberMapper.updateMember(member);
//    }
//
//    // 멤버 삭제
//    @Override
//    public void deleteMember(int memberId) {
//        memberMapper.deleteMember(memberId);
//    }
//
//    // 팀의 모든 멤버 이름 요청
//    @Override
//    public List<TeamMembersNameResponse> requestTeamMembersName(int teamId) {
//        return memberMapper.requestTeamMembersName(teamId);
//    }
//
//    // 팀에 멤버를 초대
//    @Override
//    public void requestInviteMember(int teamId, MemberModel member) {
//        member.setId(teamId);
//        memberMapper.requestInviteMember(member);
//    }
//
//    // 사용자가 초대된 팀 리스트 요청
//    @Override
//    public List<InvitedListResponse> requestInvitedList(int userId) {
//        return memberMapper.requestInvitedList(userId);
//    }
//
//    // 사용자가 멤버인 팀 리스트 요청
//    @Override
//    public List<TeamRequest> requestUserTeamList(int userId) {
//        return memberMapper.requestUserTeamList(userId);
//    }
//}
