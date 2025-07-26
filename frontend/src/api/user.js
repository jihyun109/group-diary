const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchUserInfo(userId) {
  try {
    const res = await fetch(`${BASE_URL}/users/${userId}`);
    console.log(res);
    if (!res.ok) throw new Error('유저 정보를 불러오지 못했습니다.');
    return await res.json();
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export async function fetchUserSearch(searchWord) {
  try {
    const res = await fetch(`${BASE_URL}/users/search/?searchWord=${searchWord}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!res.ok) {
      const errorData = await res.json().catch(() => ({}));
      throw new Error(errorData.message || '유저 검색 결과를 불러오지 못했습니다.');
    }
    const data = await res.json();

    return data.data;
  } catch (error) {
    throw error;
  }
}