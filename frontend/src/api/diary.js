const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchAllDiaries(userId) {
  try {
    const response = await fetch(`${BASE_URL}/diaries/all/${userId}`);
    if (!response.ok) {
      throw new Error('일기 목록을 불러오지 못했습니다.');
    }
    console.log("response: ", response);
    return await response.json();
  } catch (error) {
    console.error('일기 목록 조회 오류:', error);
    throw error;
  }
}