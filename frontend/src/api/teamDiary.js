const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchTeamDiaryList(teamId) {
    try {
        const response = await fetch(`${BASE_URL}/teamDiaries/diaryList/${teamId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.ok) {
            const data = await response.json();
            return data.data;
        }
    } catch (error) {
        console.error('팀 일기 목록 조회 오류:', error);
        throw error;
    }
}