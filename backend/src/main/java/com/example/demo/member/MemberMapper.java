package com.example.demo.member;

import com.example.demo.request.TeamRequest;
import com.example.demo.response.InvitedListResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    List<MemberModel> selectMembers();  // 모든 팀의 멤버 리스트 조회

    void insertMember(MemberModel member);  // 멤버 생성 (초대)

    void updateMember(MemberModel member);  // 멤버 수정

    void deleteMember(int memberId);    // 멤버 삭제

    List<String> requestTeamMembersName(int teamId);  // 팀의 모든 멤버 이름 요청

    void requestInviteMember(MemberModel member);   // 팀에 멤버를 초대

    List<InvitedListResponse> requestInvitedList(int userId);  // 사용자가 초대된 팀 리스트 요청

    List<TeamRequest> requestUserTeamList(int userId);  // 사용자가 멤버인 팀 리스트 요청

}
