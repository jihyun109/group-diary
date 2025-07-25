package com.example.demo.jpa;

import com.example.demo.entity.MemberEntity;
import com.example.demo.response.InvitedListResponse;
import com.example.demo.response.TeamMembersNameResponse;
import com.example.demo.response.UserTeamListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberJPARepository extends JpaRepository<MemberEntity, Long> {
    @Query("""
                SELECT new com.example.demo.response.TeamMembersNameResponse(
                    m.user_id,
                    CONCAT(u.lastName, u.firstName),
                    u.color
                )
                FROM MemberEntity m
                JOIN UserEntity u ON m.user_id = u.id
                WHERE m.team_id = :teamId AND m.status = 0
            """)
    List<TeamMembersNameResponse> findTeamMembersNameByTeamId(Long teamId);

    @Query("""
        SELECT new com.example.demo.response.InvitedListResponse(
            m.id,
            m.team_id,
            t.team_name,
            m.inviter_id,
            u.firstName,
            m.status
        )
        FROM MemberEntity m
        JOIN TeamEntity t ON m.team_id = t.id
        JOIN UserEntity u ON m.inviter_id = u.id
        WHERE m.user_id = :userId AND m.status = 1
    """)
    List<InvitedListResponse> findInvitedListByUserId(Long userId);

    @Query("""
        SELECT new com.example.demo.response.UserTeamListResponse(
            m.team_id,
            t.team_name
        )
        FROM MemberEntity m
        JOIN TeamEntity t ON m.team_id = t.id
        WHERE m.user_id = :userId AND m.status = 0
    """)
    List<UserTeamListResponse> findUserTeamListByUserId(Long userId);
}
