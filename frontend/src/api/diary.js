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

export async function createDiary(diaryData) {
  try {
    const response = await fetch(`${BASE_URL}/diaries`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(diaryData),
    });

    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message || '일기 작성에 실패했습니다.');
    }

    return await response.json().data;
  } catch (error) {
    throw error;
  }
}

export async function updateDiary(diaryId, diaryData) {
  try {
    const response = await fetch(`${BASE_URL}/diaries/edit/${diaryId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(diaryData),
    });

    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message || '일기 수정에 실패했습니다.');
    }

    return await response.json().data;
  } catch (error) {
    throw error;
  }
}

export async function fetchDiaryDetail(diaryId) {
  try {
    const response = await fetch(`${BASE_URL}/diaries/details/${diaryId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message || '일기 상세 정보를 불러오지 못했습니다.');
    }

    const data = await response.json();
    return data.data;
  } catch (error) {
    throw error;
  }
}