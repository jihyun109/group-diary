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