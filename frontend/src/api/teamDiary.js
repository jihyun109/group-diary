const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchTeamDiaryList(teamId) {
  try {
    const response = await fetch(
      `${BASE_URL}/teamDiaries/diaryList/${teamId}`,
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );

    if (response.ok) {
      const data = await response.json();
      return data.data;
    }
  } catch (error) {
    console.error('팀 일기 목록 조회 오류:', error);
    throw error;
  }
}

export async function shareDiary(diaryId, teamId) {
  try {
    const shareDiaryData = {
      diary_id: diaryId,
      team_id: teamId,
    };

    const response = await fetch(`${BASE_URL}/teamDiaries`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(shareDiaryData),
    });

    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message || '일기 공유에 실패했습니다.');
    }

    return await response.json();
  } catch (error) {
    throw error;
  }
}
