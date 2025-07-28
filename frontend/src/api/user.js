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

export async function logInUser(email, password) {
  try {
    const response = await fetch(`${BASE_URL}/users/logIn`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password }),
    });
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return await response.json();
  } catch (error) {
    console.error('로그인 API 호출 중 오류:', error);
    throw error;
  }
}

export async function signUpUser({ firstName, lastName, email, password, color }) {
  try {
    const response = await fetch(`${BASE_URL}/users`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ firstName, lastName, email, password, color }),
    });
    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      throw new Error(errorData.message || '회원가입에 실패했습니다.');
    }
    return await response.json();
  } catch (error) {
    console.error('회원가입 API 호출 중 오류:', error);
    throw error;
  }
}