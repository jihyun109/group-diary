package com.example.demo.jpa;

import com.example.demo.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryJPARepository extends JpaRepository<DiaryEntity, Long> {
    @Query("""
            SELECT d
            FROM DiaryEntity d
            JOIN UserEntity u ON d.writerId = u.id
            WHERE d.id = :diaryId
            """
    )
    Optional<DiaryEntity> findDiaryWithWriterById(Long diaryId);

    @Query("""
        SELECT DISTINCT d FROM DiaryEntity d
        JOIN TeamDiaryEntity td ON d.id = td.diary_id
        JOIN MemberEntity m ON td.team_id = m.team_id
        JOIN UserEntity u ON d.writerId = u.id
        WHERE m.user_id = :userId AND m.status = 0
    """)
    List<DiaryEntity> findAllTeamDiariesByUserId(Long userId);

    Optional<DiaryEntity> findByDiaryTitleAndWrittenDateAndWriterId(String diaryTitle, String writtenDate, Long writerId);
}
