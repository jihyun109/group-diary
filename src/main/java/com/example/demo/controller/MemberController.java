package com.example.demo.controller;

import com.example.demo.entity.MemberEntity;
import com.example.demo.dto.response.TeamRequest;
import com.example.demo.dto.request.InvitedListResponse;
import com.example.demo.dto.request.TeamMembersNameResponse;
import com.example.demo.service.inter.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 모든 팀의 멤버 리스트 조회
    @GetMapping("/members")
    public HashMap<String, Object> getMembers() {
        List<MemberEntity> data = memberService.getMembers();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 멤버 생성 (초대)
    @PostMapping("/members")
    public HashMap<String, String> insertMember(@RequestBody MemberEntity member) {

        memberService.insertMember(member);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 멤버 수정
    @PutMapping("/members/{id}")
    public HashMap<String, String> updateMember(@RequestBody MemberEntity memberData, @PathVariable(required = true) int id) {

        memberService.updateMember(id, memberData);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 멤버 삭제
    @DeleteMapping("/members/{id}")
    public HashMap<String, String> deleteMember(@PathVariable(required = true) int id, @RequestParam(defaultValue = "succ") String succMsg) {

        memberService.deleteMember(id);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", succMsg);
        return result;
    }

    // 팀에 속한 모든 멤버의 이름 요청
    @GetMapping("/members/{teamId}")
    public HashMap<String, Object> requestTeamMembersName(@PathVariable(required = true) int teamId) {
        List<TeamMembersNameResponse> data = memberService.requestTeamMembersName(teamId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 팀에 멤버를 초대
    @PostMapping("/members/{teamId}")
    public HashMap<String, String> requestInviteMember(@PathVariable(required = true) int teamId, @RequestBody MemberEntity member) {

        memberService.requestInviteMember(teamId, member);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 사용자가 초대된 팀 리스트 요청
    @GetMapping("/members/invited/{userId}")
    public HashMap<String, Object> requestInvitedList(@PathVariable(required = true) int userId) {
        List<InvitedListResponse> data = memberService.requestInvitedList(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 사용자가 멤버인 팀 리스트 요청
    @GetMapping("/members/userTeamList/{userId}")
    public HashMap<String, Object> requestUserTeamList(@PathVariable(required = true) int userId) {
        List<TeamRequest> data = memberService.requestUserTeamList(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }


}
