package com.example.demo.controller;

import com.example.demo.dto.request.MemberInviteRequest;
import com.example.demo.dto.request.MemberUpdateRequest;
import com.example.demo.dto.response.MemberListResponse;
import com.example.demo.dto.request.TeamRequest;
import com.example.demo.dto.response.InvitedListResponse;
import com.example.demo.service.inter.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class GroupMemberController {

    private final MemberService memberService;

    // 모든 팀의 멤버 리스트 조회
    @GetMapping()
    public HashMap<String, Object> getMembers() {
        List<MemberListResponse> data = memberService.getMembers();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    // 멤버 생성 (초대)
    // TODO: requestbody -> param 으로 바꾸기
    @PostMapping("/{groupId}/invite")
    public HashMap<String, String> inviteMember(@RequestBody MemberInviteRequest memberInviteRequest) {
        memberService.inviteMember(memberInviteRequest);

        HashMap<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    // 초대 수락


    // 멤버 수정
    @PutMapping("/members/{id}")
    public HashMap<String, String> updateMember(@RequestBody MemberUpdateRequest memberData, @PathVariable(required = true) int id) {

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
    public HashMap<String, Object> requestTeamMembersName(@PathVariable(required = true) long teamId) {
        List<String> data = memberService.requestTeamMembersName(teamId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
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
