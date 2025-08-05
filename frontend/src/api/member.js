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

export async function inviteUser(inviteeId, teamId, inviterId) {
  const inviteData = {
    userId: inviteeId,
    teamId: teamId,
    status: inviteeId === inviterId ? 0 : 1,
    inviterId: inviterId,
  };

  try {
    const response = await fetch(`${BASE_URL}/members`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(inviteData),
    });

    if (response.ok) {
      console.log('초대 성공: ', inviteData);
    } else {
      const errorData = await response.json();
      console.log(`오류가 발생했습니다: ${JSON.stringify(errorData)}`);
    }
  } catch (error) {
    console.error('초대 오류:', error);
    throw error;
  }
}

export async function fetchTeamMembers(teamId) {
  try {
    const response = await fetch(`${BASE_URL}/members/${teamId}`);
    const data = await response.json();
    return data.data;
    if (!response.ok) {
      throw new Error('팀 멤버 목록을 불러오지 못했습니다.');
    }
  } catch (error) {
    console.error('팀 멤버 목록 조회 오류:', error);
    throw error;
  }
}
