package com.example.demo.mapper;

import com.example.demo.dto.TeamCreateRequestDTO;
import com.example.demo.model.TeamModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeamMapper {

    // 팀 생성
    void insertTeam(TeamCreateRequestDTO team);

    List<TeamModel> searchTeamByDiaryId(long diaryId);

    // 팀 id 조회
    List<TeamModel> findTeamId(String teamName, long creatorId);
}
