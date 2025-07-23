export async function fetchAllDiaries(userId) {
  const res = await fetch(`${BASE_URL}/diaries/all/${userId}`);
  if (!res.ok) throw new Error('전체 일기 목록을 불러오지 못했습니다.');
  return (await res.json()).data;
}
