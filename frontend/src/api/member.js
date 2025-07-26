const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchUserTeams(userId) {
  try {
    const response = await fetch(`${BASE_URL}/members/userTeamList/${userId}`);
    if (!response.ok) {
      throw new Error('팀 목록을 불러오지 못했습니다.');
    }
    const data = await response.json();
    return data.data;
  } catch (error) {
    console.error('팀 목록 조회 오류:', error);
    throw error;
  }
}

export async function fetchUserInvites(userId) {
  try {
    const response = await fetch(`${BASE_URL}/members/invited/${userId}`);
    if (!response.ok) {
      throw new Error('초대 목록을 불러오지 못했습니다.');
    }
    const data = await response.json();
    return data.data;
  } catch (error) {
    console.error('초대 목록 조회 오류:', error);
    throw error;
  }
}