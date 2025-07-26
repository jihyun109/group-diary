const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchAllDiaries(userId) {
  try {
    const res = await fetch(`${BASE_URL}/diaries/all/${userId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!res.ok) {
      const errorData = await res.json().catch(() => ({}));
      throw new Error(errorData.message || '전체 일기 목록을 불러오지 못했습니다.');
    }
    const data = await res.json();
    console.log("data: ", data);

    return data.data;
  } catch (error) {
    throw error;
  }
}