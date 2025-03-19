//package com.example.demo.legacy.mapperImpl;
//
//import com.example.demo.dto.request.AllTeamDiariesResponse;
//import com.example.demo.entity.DiaryModel;
//import com.example.demo.mapper.DiaryMapper;
//import com.example.demo.mapper.TeamMapper;
//import com.example.demo.service.inter.DiaryService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DiaryServiceMapperImpl implements DiaryService {
//    private DiaryMapper diaryMapper;
//    private TeamMapper teamMapper;
//
//    public DiaryServiceMapperImpl(DiaryMapper diaryMapper, TeamMapper teamMapper) {
//        this.teamMapper=teamMapper;
//        this.diaryMapper = diaryMapper;
//    }
//
//    // 모든 일기 리스트 조회
//    @Override
//    public List<DiaryModel> getDiaries() {
//        return diaryMapper.selectDiaries();
//    }
//
//    // 일기 생성
//    @Override
//    public void insertDiary(DiaryModel diary) {
//        diaryMapper.insertDiary(diary);
//    }
//
//    // 일기 수정
//    @Override
//    public void updateDiary(int diaryId, DiaryModel diary) {
//        diary.setId(diaryId);
//        diaryMapper.updateDiary(diary);
//    }
//
//    // 일기 삭제
//    @Override
//    public void deleteDiary(int diaryId) {
//        diaryMapper.deleteDiary(diaryId);
//    }
//
//    // 사용자가 포함된 모든 그룹의 일기 정보 요청
//    @Override
//    public List<AllTeamDiariesResponse> requestAllTeamDiaries(int userId) {
//        return diaryMapper.requestAllTeamDiaries(userId);
//    }
//
//    // 선택한 다이어리 상세 정보 요청
//    @Override
//    public DiaryModel requestDiaryDetails(int diaryId) {
//        DiaryModel diary= diaryMapper.requestDiaryDetails(diaryId);
//        diary.setSharedTeamList(teamMapper.searchTeamByDiaryId(diaryId));
//        return diary;
//    }
//
//    // 일기 id 요청
//    @Override
//    public List<DiaryModel> requestDiaryId(String diaryTitle, String writtenDate, int writerId) {
//        return diaryMapper.requestDiaryId(diaryTitle, writtenDate, writerId);
//    }
//
//
//
//}
