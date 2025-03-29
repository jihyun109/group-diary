package com.example.demo.repository;

import com.example.demo.dto.request.TeamRequest;
import com.example.demo.dto.response.InvitedListResponse;
import com.example.demo.entity.GroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<GroupMemberEntity, Long> {
    @Query("""
            SELECT u.first_name
            FROM UserEntity u
            WHERE u IN
            (SELECT tm.user            FROM GroupMemberEntity tm            WHERE tm.team.id = :teamId)
            """)
    List<String> findMembersByTeamId(@Param("teamId") long teamId);

    @Query("""
            SELECT tm.team
            FROM GroupMemberEntity tm
            WHERE tm.user.id = :userId AND tm.status = 'INVITED'
            """)
    List<InvitedListResponse> requestInvitedList(@Param("userId")long userId);

    @Query("""
            SELECT tm.team
            FROM GroupMemberEntity tm
            WHERE tm.user.id = :userId AND tm.status = 'JOINED'
            """)
    List<TeamRequest> requestUserTeamList(@Param("userId")long userId);
}
