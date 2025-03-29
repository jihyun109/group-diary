package com.example.demo.repository;

import com.example.demo.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    @Query("""
                SELECT d FROM DiaryEntity d
                WHERE d.id IN (
                    SELECT td.diary_id FROM TeamDiaryEntity td
                    WHERE td.team_id IN (
                        SELECT m.team.id FROM TeamMemberEntity m
                        WHERE m.user.id = :userId
                    )
                )
            """)
    List<DiaryEntity> getAllTeamDiaries(@Param("userId") Long userId);

    @Query("""
                SELECT id FROM DiaryEntity
                WHERE diary_title = :title AND written_date = :writtenDate AND writer_id = :writerId
            """)
    Long findDiaryId(@Param("title") String diaryTitle, @Param("writtenDate") String writtenDate, @Param("writerId") Long writerId);
}
