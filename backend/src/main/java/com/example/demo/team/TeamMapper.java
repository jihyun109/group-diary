package com.example.demo.team;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeamMapper {

    // 팀 리스트 조회
    List<TeamModel> selectTeams();

    // 팀 생성
    void insertTeam(TeamModel team);

    // 팀 수정
    void updateTeam(TeamModel team);

    // 팀 삭제
    void deleteTeam(int teamId);

    List<TeamModel> searchTeam(String searchWord);

    List<TeamModel> searchTeamByDiaryId(long diaryId);

    // 팀 id 조회
    List<TeamModel> findTeamId(String teamName, int creatorId);

    // 사용자가 속한 팀 만들기 요청
//    void requestCreateTeam(CreateTeamRequest team);

}
