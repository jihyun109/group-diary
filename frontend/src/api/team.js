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
    } else {
      const errorData = await response.json();
      alert(`오류가 발생했습니다: ${errorData.message}`);
    }
  } catch (error) {
    alert(`네트워크 오류가 발생했습니다: ${error.message}`);
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
      const teamId = resjson.data;
      return teamId;
    } else {
      const errorData = await response.json();
      console.log(`team not founded: ${errorData.message}`);
    }
  } catch (error) {
    console.log(
      `team not founded. 네트워크 오류가 발생했습니다: ${error.message}`
    );
  }
}