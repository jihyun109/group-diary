package com.example.demo.controller;

import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.member.MemberModel;
import com.example.demo.service.MemberService;
import com.example.demo.service.MemberServiceImpl;
import com.example.demo.request.TeamRequest;
import com.example.demo.response.InvitedListResponse;
import com.example.demo.response.TeamMembersNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    private final MemberService memberService;

    // 멤버 생성 (초대)
    @PostMapping("/members")
    public ResponseEntity<String> insertMember(@RequestBody MemberInviteRequestDTO member) {
        memberService.insertMember(member);

        return ResponseEntity.ok("멤버 초대 완료");
    }

    // 모든 팀의 멤버 리스트 조회
    @GetMapping("/members")
    public HashMap<String, Object> getMembers() {
        List<MemberModel> data = memberServiceImpl.getMembers();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 멤버 수정
    @PutMapping("/members/{id}")
    public HashMap<String, String> updateMember(@RequestBody MemberModel memberData, @PathVariable(required = true) int id) {

        memberServiceImpl.updateMember(id, memberData);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 멤버 삭제
    @DeleteMapping("/members/{id}")
    public HashMap<String, String> deleteMember(@PathVariable(required = true) int id, @RequestParam(defaultValue = "succ") String succMsg) {

        memberServiceImpl.deleteMember(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    // 팀에 속한 모든 멤버의 이름 요청
    @GetMapping("/members/{teamId}")
    public HashMap<String, Object> requestTeamMembersName(@PathVariable(required = true) int teamId) {
        List<TeamMembersNameResponse> data = memberServiceImpl.requestTeamMembersName(teamId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 팀에 멤버를 초대
    @PostMapping("/members/{teamId}")
    public HashMap<String, String> requestInviteMember(@PathVariable(required = true) int teamId, @RequestBody MemberModel member) {

        memberServiceImpl.requestInviteMember(teamId, member);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 사용자가 초대된 팀 리스트 요청
    @GetMapping("/members/invited/{userId}")
    public HashMap<String, Object> requestInvitedList(@PathVariable(required = true) int userId) {
        List<InvitedListResponse> data = memberServiceImpl.requestInvitedList(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 사용자가 멤버인 팀 리스트 요청
    @GetMapping("/members/userTeamList/{userId}")
    public HashMap<String, Object> requestUserTeamList(@PathVariable(required = true) int userId) {
        List<TeamRequest> data = memberServiceImpl.requestUserTeamList(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }


}
