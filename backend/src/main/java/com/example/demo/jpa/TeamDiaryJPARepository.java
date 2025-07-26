package com.example.demo.jpa;

import com.example.demo.entity.TeamDiaryEntity;
import com.example.demo.response.SharedTeamsResponse;
import com.example.demo.response.TeamDiaryListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDiaryJPARepository extends JpaRepository<TeamDiaryEntity, Long> {
    @Query("""
                SELECT new com.example.demo.response.TeamDiaryListResponse(
                    d.id,
                    u.firstName,
                    u.lastName,
                    u.initial,
                    u.color,
                    d.writtenDate,
                    d.diaryTitle
                )
                FROM TeamDiaryEntity td
                JOIN DiaryEntity d ON td.diary_id = d.id
                JOIN UserEntity u ON d.writerId = u.id
                WHERE td.team_id = :teamId
            """)
    List<TeamDiaryListResponse> findTeamDiariesByTeamId(Long teamId);

    @Query("""
                SELECT new com.example.demo.response.SharedTeamsResponse(
                    td.team_id,
                    t.team_name
                )
                FROM TeamDiaryEntity td
                JOIN TeamEntity t ON td.team_id = t.id
                WHERE td.diary_id = :diaryId
            """)
    List<SharedTeamsResponse> findSharedTeamsByDiaryId(Long diaryId);

    @Query("""
                DELETE FROM TeamDiaryEntity td
                WHERE td.diary_id = :diaryId AND td.team_id = :teamId
            """)
    void deleteByDiaryIdAndTeamId(Long diaryId, Long teamId);
}
