package com.example.demo.controller;

import com.example.demo.dto.MemberInviteRequestDTO;
import com.example.demo.dto.MemberUpdateRequestDTO;
import com.example.demo.service.MemberService;
import com.example.demo.request.TeamRequest;
import com.example.demo.response.InvitedListResponse;
import com.example.demo.response.TeamMembersNameResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "팀에 멤버로 초대")
    @PostMapping("/members")
    public ResponseEntity<String> insertMember(@RequestBody MemberInviteRequestDTO member) {
        memberService.insertMember(member);

        return ResponseEntity.ok("멤버 초대 완료");
    }

    @Operation(summary = "멤버 수정 (초대 수락/거절)")
    @PutMapping("/members/{id}")
    public ResponseEntity<String> updateMember(@RequestBody MemberUpdateRequestDTO memberData, @PathVariable(required = true) long id) {
        memberData.setId(id);
        log.warn("inviterId: " + memberData.getInviterId());
        log.warn("userId:" + memberData.getUserId());
        memberService.updateMember(memberData);

        log.warn("inviterId: " + memberData.getInviterId());
        log.warn("userId:" + memberData.getUserId());
        return ResponseEntity.ok("멤버 정보 수정 완료");
    }

    @Operation(summary = "멤버 삭제 (초대 거절)")
    @DeleteMapping("/members/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable(required = true) long id, @RequestParam(defaultValue = "succ") String succMsg) {
        memberService.deleteMember(id);

        return ResponseEntity.ok("invite refused");
    }

    @Operation(summary = "팀에 속한 모든 멤버의 이름 요청")
    @GetMapping("/members/{teamId}")
    public HashMap<String, Object> requestTeamMembersName(@PathVariable(required = true) long teamId) {
        List<TeamMembersNameResponse> data = memberService.requestTeamMembersName(teamId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @Operation(summary = "사용자가 초대된 팀 리스트 요청")
    @GetMapping("/members/invited/{userId}")
    public HashMap<String, Object> requestInvitedList(@PathVariable(required = true) long userId) {
        List<InvitedListResponse> data = memberService.requestInvitedList(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }

    @Operation(summary = "사용자가 멤버인 팀 리스트 요청")
    @GetMapping("/members/userTeamList/{userId}")
    public HashMap<String, Object> requestUserTeamList(@PathVariable(required = true) long userId) {
        List<TeamRequest> data = memberService.requestUserTeamList(userId);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", data);
        return result;
    }
}
