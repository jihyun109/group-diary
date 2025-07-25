package com.example.demo.jpa;

import com.example.demo.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeamJPARepository extends JpaRepository<TeamEntity, Long> {
    @Query("SELECT t FROM TeamEntity t WHERE t.team_name = :team_name AND t.creator_id = :creator_id")
    Optional<TeamEntity> findTeamByTeam_nameAndCreator_id(String team_name, Long creator_id);

    @Query("""
        SELECT t FROM TeamEntity t
        JOIN TeamDiaryEntity td ON t.id = td.team_id
        WHERE td.diary_id = :diaryId
    """)
    Optional<TeamEntity> findTeamByDiaryId(Long diaryId);
}
