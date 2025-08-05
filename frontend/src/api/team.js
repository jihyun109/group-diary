const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function createTeam(userId, groupNameToCreate) {
  try {
    const response = await fetch(`${BASE_URL}/teams`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        team_name: groupNameToCreate,
        creator_id: userId,
      }),
    });

    if (response.ok) {
      const responseData = await response.json();
      return responseData.data;
    } else {
      const errorData = await response.json();
      throw new Error(errorData.message || '팀 생성 오류');
    }
  } catch (error) {
    throw error;
  }
}

export async function fetchTeamId(teamName, creatorId) {
  try {
    const response = await fetch(
      `${BASE_URL}/teams/findId?teamName=${encodeURIComponent(teamName)}&creatorId=${creatorId}`,
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      }
    );

    if (response.ok) {
      const resjson = await response.json();
      return resjson.data;
    } else {
      const errorData = await response.json();
      throw new Error(errorData.message || '팀 조회 오류');
    }
  } catch (error) {
    throw error;
  }
}
