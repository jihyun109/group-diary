<script>
import { mapState } from 'vuex';

export default {
  components: {
  },

  mounted() {
    this.$store.dispatch('fetchData');
    this.fetchData();
    // console.log(this.diaryData);
  },

  data() {
    return {
      // userId: 0,
      color: '',
      searchWord: '',
      groupNameToCreate: '',

      dataList: null,
      dataTypeMap: new Map(),
      diaryData: null,
      teamData: null,
      membersData: null,
      usersData: null,
      userSearchData: [],
      inviteData: null,
      usersToInvite: [],

      str: '',

      isCalendar: false,
      dropdownText: 'list',
      // 사용자가 속한 team의 이름을 받아와서 배열에 저장.
      // team name 클릭하면 team page로 이동. (team 정보를 가지고서.),

      errors: {
        groupNameToCreate: false
      }
    }
  },

  computed: {
    ...mapState(['userId', 'firstName', 'lastName']),

    filteredUserSearchData() {
      const invitedUserIds = new Set(this.usersToInvite.map(user => user.userId));
      console.log("invitedUserIds:", invitedUserIds);
      return this.userSearchData.filter(user => user.id !== this.userId && !invitedUserIds.has(user.id));
    }
  },

  methods: {
    async fetchData() {
      console.log(this.firstName);
      // console.log(this.userId);

      // this.userId = this.$store.state.userId;
      const menuList = await Promise.all([
        { type: 'diaries', url: `http://localhost:8080/diaries` },
        { type: 'teams', url: `http://localhost:8080/members/userTeamList/${this.userId}` },
        { type: 'members', url: `http://localhost:8080/members` },
        { type: 'users', url: `http://localhost:8080/users` },
        { type: 'invites', url: `http://localhost:8080/members/invited/${this.userId}` }
      ])

      const requests = menuList.map(async (dataReq) => {
        const res = await fetch(dataReq.url)
        return res.json()
      })

      this.dataList = await Promise.all(requests)
      this.dataTypeMap = new Map(this.dataList.map((data, idx) => [menuList[idx].type, data.data]))
      // console.log(this.dataTypeMap, 'dm')
      this.diaryData = this.dataTypeMap.get('diaries')
      this.teamData = this.dataTypeMap.get('teams')
      this.membersData = this.dataTypeMap.get('members')
      this.usersData = this.dataTypeMap.get('users')
      this.inviteData = this.dataTypeMap.get('invites')

      // console.log(this.inviteData);

      // this.alarmN = this.inviteData ? this.inviteData.length : 0;

      // console.log(this.diariesData, this.teamsData, this.membersData, this.usersData)
    },

    // 일기 리스트 보기 방식 변경
    setView(text) {
      this.dropdownText = text
      this.isCalendar = text === 'calendar'
    },

    // 그룹 초대 수락
    async requestAcceptInvite(invite) {
      // console.log(alarm)
      try {
        const response = await fetch(`http://localhost:8080/members/${invite.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            user_id: this.userId,
            status: 0,
            team_id: invite.team_id,
            inviter_id: invite.inviter_id

          })
        });
        if (response.ok) {
          // inviteData 업데이트
          // const response = await fetch(`http://localhost:8080/members/invited/${this.userId}`)
          // const resjson = await response.json();
          // this.$store.commit("setinviteData", resjson.data);

          this.inviteData = this.inviteData.filter(invite => invite.id !== invite.id);
          this.alarmN = this.inviteData.length;
          this.fetchData()
        } else {
          console.error('Error accepting invite');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

    // 그룹 초대 거절
    async requestRefuseInvite(inviteId) {
      try {
        const response = await fetch(`http://localhost:8080/members/${inviteId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (response.ok) {
          // inviteData 업데이트
          // const responseInviteList = await fetch(`http://localhost:8080/members/invited/${this.userId}`)
          // const resjson = await responseInviteList.json();
          // this.$store.commit("setinviteData", resjson.data);

          this.inviteData = this.inviteData.filter(invite => invite.id !== inviteId);
          this.alarmN = this.inviteData.length;
        } else {
          console.error('Error accepting invite');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

    // 사용자 검색
    async searchUser() {
      try {
        const response = await fetch(`http://localhost:8080/users/search/?searchWord=${encodeURIComponent(this.searchWord)}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (response.ok) {
          const resjson = await response.json();
          this.userSearchData = resjson.data;
          console.log(this.userSearchData);
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },


    // log out
    logOut() {
      this.$store.commit('logOut');
      this.$router.push({ path: 'logIn' });
      console.log(this.userId);
    },

    // diary detail 페이지로 이동
    moveToDetails(diaryId) {
      // console.log(diaryId)
      this.$router.push({ path: 'diaryDetail', query: { diary: diaryId } });
    },

    // 해당 team 페이지로 이동
    moveToTeamPage(teamId) {
      console.log(teamId);
      this.$router.push({ path: 'teamPage', query: { team: teamId } });
    },

    // 초대 그룹에 추가
    addToInviteGroup(userId, lastName, firstName) {
      this.usersToInvite.push({ userId, lastName, firstName });
      console.log("userToInvite[]: ", this.usersToInvite);

    },

    // 초대 그룹에서 삭제
    removeFromInviteGroup(userId) {
      console.log(userId);
      this.usersToInvite = this.usersToInvite.filter(user => user.userId != userId);
      console.log("userToInvite[]: ", this.usersToInvite);
    },

    // usersToInvite 배열 초기화
    resetUsersToInvite() {
      this.usersToInvite = []
      console.log("userToInvite[]: ", this.userToInvite);
    },

    // create team
    async createTeam() {
      await this.requestCreateTeam();
      await this.inviteUsers();
      alert('팀이 생성되었습니다.');
      const modal = new bootstrap.Modal(document.getElementById('createGroup-form'));
      modal.hide();
      this.fetchData();
    },

    // 팀생성 요청 보내기
    async requestCreateTeam() {
      // 오류 상태 초기화
      this.errors.groupNameToCreate = !this.groupNameToCreate;

      // 오류가 있는 경우 경고 메시지 표시
      if (this.errors.groupNameToCreate) {
        alert('그룹 이름을 입력해 주세요');
        return;
      }

      // // 입력 데이터를 객체로 수집
      // const groupData = {
      //   team_name: this.groupNameToCreate,
      //   creator_id: this.userId
      // };

      try {
        // 서버로 POST 요청 보내기
        const response = await fetch('http://localhost:8080/teams', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            team_name: this.groupNameToCreate,
            creator_id: this.userId
          })
        });

        if (response.ok) {
          // 요청이 성공하면 성공 메시지 표시
          console.log('그룹 생성 완료. group name: ', this.groupNameToCreate);
          const responseData = await response.json();

        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          alert(`오류가 발생했습니다: ${errorData.message}`);
        }
      } catch (error) {
        // 네트워크 오류 처리
        alert(`네트워크 오류가 발생했습니다: ${error.message}`);
      }
    },

    async inviteUsers() {
      // 추가한 team id 찾기
      const teamData = await this.requestTeamId();
      const teamId = teamData[0].id;

      // 본인 그룹에 추가
      this.requestInviteUser(this.userId, teamId);

      // 선택한 사용자들 초대.
      for (let i = 0; i < this.usersToInvite.length; i++) {
        let userId = this.usersToInvite[i].userId;
        console.log('userId:', userId, ', teamId: ', teamId)
        this.requestInviteUser(userId, teamId);
      }

    },

    // 초대 요청 보내기
    async requestInviteUser(userId, teamId) {
      // 입력 데이터를 객체로 수집
      const inviteData = {
        user_id: userId,
        team_id: teamId,
        status: userId === this.userId ? 0 : 1,
        inviter_id: this.userId
      };

      try {
        // 서버로 POST 요청 보내기
        const response = await fetch('http://localhost:8080/members/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(inviteData)
        });

        if (response.ok) {
          // 요청이 성공하면 성공 메시지 표시
          console.log("초대 성공: ", inviteData);
        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          console.log(`오류가 발생했습니다: ${errorData.message}`);
        }
      } catch (error) {
        // 네트워크 오류 처리
        alert(`네트워크 오류가 발생했습니다: ${error.message}`);
      }
    },

    async requestTeamId() {
      // // 입력 데이터를 객체로 수집
      // const groupData = {
      //   groupNameToCreate: this.groupNameToCreate,
      //   creator_id: this.userId
      // };

      try {
        console.log('store userId: ', this.userId)
        const response = await fetch(`http://localhost:8080/teams/findId?teamName=${encodeURIComponent(this.groupNameToCreate)}&creatorId=${this.userId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          },
          // body: JSON.stringify(groupData)
        });

        console.log("response: ", response)

        if (response.ok) {
          const resjson = await response.json();
          console.log(resjson);
          const teamId = resjson.data;
          console.log('find team id. teamId: ', teamId);
          return teamId;
        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          console.log(`team not founded: ${errorData.message}`)
        }
      } catch (error) {
        // 네트워크 오류 처리
        console.log(`team not founded. 네트워크 오류가 발생했습니다: ${error.message}`);
      }
    }

  },
}

</script>

<template>
  <div>
    <p v-if="!dataList">로딩...</p>
    <div v-else>
      <div class="row">
        <div class="col-2">
          <!-- create group 버튼 -->
          <button type="button btn-block" class="btn bg-gradient-info" data-bs-toggle="modal"
            data-bs-target="#createGroup-form">create group</button>

          <!-- modal -->
          <div class="modal fade" id="createGroup-form" tabindex="-1" role="dialog" aria-labelledby="createGroup-form"
            aria-hidden="true" data-bs-backdrop="static">
            <div class="modal-dialog modal-dialog-centered " role="document">
              <div class="modal-content">
                <!-- 헤더 -->
                <div class="modal-header">
                  <h5 class="modal-title" id="modal-title-notification">Create new group</h5>
                  <!-- 닫기 버튼 색 바꾸기 -->
                  <!-- <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close">
                    <span aria-hidden="true">×</span>
                  </button> -->
                </div>
                <!-- body -->
                <div class="modal-body p-0">
                  <div class="card card-plain">

                    <div class="card-body">
                      <form role="form text-left d-flex">
                        <label>Group name</label>
                        <div class="input-group input-group-outline mb-3">
                          <label class="form-label">Group name</label>
                          <input v-model="groupNameToCreate" type="text" class="form-control"
                            :class="{ 'is-invalid': errors.groupNameToCreate }">
                        </div>

                        <!-- <form class="d-flex" role="search"> -->
                        <label>Add friends to this group</label>
                        <!-- 버튼 클릭 -> searchWord에 해당되는 user 리스트 보이기
                        searchData : searchWord에 해당되는 user 리스트,
                        -->

                        <div id="recipient_input_list">
                          <span v-for="(user, idx) in usersToInvite" :key="idx"
                            class="badge align-items-center p-1 pe-2 text-success-emphasis bg-success-subtle border border-success-subtle rounded-pill">
                            <img class="rounded-circle me-1" width="24" height="24" src="https://github.com/mdo.png"
                              alt="">
                            {{ user.lastName }} {{ user.firstName }}
                            <span class="vr mx-2"></span>
                            <a href="javacsript:void(0);" @click="removeFromInviteGroup(user.userId)">
                              <span class="material-icons opacity-6 me-2 text-md">cancel</span>
                            </a>
                          </span>
                        </div>

                        <form @submit.prevent="searchUser">
                          <div class="input-group input-group-outline mb-3">
                            <div class="col-8">
                              <input v-model="searchWord" class="form-control me-2" type="text" placeholder="Search">
                            </div>
                            <div class="col-4 ps-0">
                              <button @click="searchUser" class="btn btn-outline-success ms-3"
                                id="searchBtn">Search</button>
                            </div>
                          </div>
                        </form>

                        <div v-if="filteredUserSearchData.length === 0" class="list-group">
                          <p>no such user</p>
                        </div>
                        <div v-else class="list-group">
                          <a v-for="(user, idx) in filteredUserSearchData" :key="idx"
                            @click="addToInviteGroup(user.id, user.last_name, user.first_name)"
                            href="javacsript:void(0);" class="list-group-item list-group-item-action">
                            {{ user.last_name }} {{ user.first_name }}
                          </a>
                        </div>
                        <!-- </form> -->
                      </form>
                    </div>
                  </div>
                </div>

                <!-- modal footer -->
                <div class="modal-footer">
                  <button @click="resetUsersToInvite" type="button" class="btn bg-gradient-secondary"
                    data-bs-dismiss="modal">Close</button>
                  <button @click="createTeam" type="button" class="btn bg-gradient-primary">Create</button>
                </div>
              </div>
            </div>
          </div>

          <!-- team 목록 -->
          <div class="list-group" v-if="teamData">
            <a @click="moveToTeamPage(team.team_id)" href="javacsript:void(0);"
              class="list-group-item list-group-item-action" v-for="(team, idx) in teamData" :key="idx">
              {{ team.team_name }}
            </a>
          </div>
        </div>
        <div class="col-10">
          <div class="row">
            <div class="d-flex align-items-center">
              <div class="dropdown mx-1">

                <!-- 초대 알림 -->
                <!-- 버튼 -->
                <button type="button" class="btn btn-info position-relative dropdown-toggle" data-bs-toggle="dropdown"
                  data-bs-auto-close="false" aria-expanded="false">
                  <span>invite alarms </span>
                  <!-- badge -->
                  <span class="badge badge-sm badge-circle badge-danger border border-white border-2">
                    {{ inviteData.length }}
                  </span>
                </button>
                <!-- dropdown -->
                <ul class="dropdown-menu">
                  <ul class="list-group">
                    <li class="list-group-item" v-for="(invite, idx) in inviteData" :key="idx">
                      <p>invited from ' {{ invite.first_name }} '</p>
                      <p>group name: {{ invite.team_name }}</p>
                      <row>
                        <button @click="requestAcceptInvite(invite)" type="button"
                          class="btn bg-gradient-info">accept</button>
                        <button @click="requestRefuseInvite(invite.id)" type="button"
                          class="btn bg-gradient-danger">refuse</button>
                      </row>
                    </li>
                  </ul>
                </ul>

              </div>
              <div class="btn-group mx-1">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                  data-bs-auto-close="true" aria-expanded="false">
                  {{ dropdownText }}
                </button>
                <ul class="dropdown-menu">
                  <button class="dropdown-item" @click="setView('list')">list</button>
                  <button class="dropdown-item" @click="setView('calendar')">calendar</button>

                </ul>
              </div>
              <a href="/writeDiary" class="btn btn-info mx-1">write diary</a>

              <!-- userInfo -->
              <a class="nav-link" href="/userInfo">
                <div>
                  {{ this.lastName }}
                  {{ this.firstName }}
                </div>
              </a>
              <!-- 사용해보기 -->
              <a class="icon-link mb-1" href="/docs/5.3/getting-started/introduction/">
                <svg class="bi" width="16" height="16">
                  <use xlink:href="#arrow-right-circle"></use>
                </svg>
                Bootstrap quick start guide
              </a>

              <!-- log out -->
              <button type="button" class="btn btn-twitter">
                <span @click="logOut" class="btn-inner--text">log-out</span>
              </button>

            </div>
          </div>
          <div class="row">
            <div class="col-6">

            </div>
            <div class="col-6">
            </div>
          </div>

          <!-- 일기 리스트 -->
          <!-- 달력 보기 방식 -->
          <h1 v-if="isCalendar">calendar</h1>
          <!-- 리스트 보기 방식 -->
          <div v-else>
            <div class="card">
              <div class="table-responsive">
                <table class="table align-items-center mb-0">
                  <!-- 표 헤더 -->
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Author</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Title</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Date
                      </th>
                      <!-- <th class="text-secondary opacity-7"></th> -->
                    </tr>
                  </thead>
                  <!-- 표 body -->
                  <tbody v-for="(diary, idx) in diaryData" :key="idx">
                    <!-- 한 행 -->
                    <tr>
                      <!-- author -->
                      <td>
                        <div class="d-flex px-2 py-1">
                          <button type="button" class="btn btn-facebook btn-icon-only rounded-circle" :class="setColor">
                          </button>
                        </div>
                      </td>
                      <!-- title -->
                      <td>
                        <h6 class=" mb-0"><a href="javacsript:void(0);" @click="moveToDetails(diary.id)">{{
                          diary.diary_title
                            }}</a></h6>
                      </td>
                      <!-- Date -->
                      <td class="align-middle text-center">
                        <span class="text-secondary font-weight-normal">{{ diary.written_date }}</span>
                      </td>
                    </tr>
                  </tbody>

                  <!-- 리스트 보기 2
                  <thead>
                    <tr>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Author</th>
                      <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Title</th>
                      <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Date
                      </th>
                      <th class="text-secondary opacity-7"></th>
                    </tr>
                  </thead>
                  <ul v-for="(diary, idx) in diaryData" :key="idx" class="list-group">
                    <a a href="javacsript:void(0);" @click="moveToDetails" class="list-group-item">
                      <div class="d-flex px-2 py-1">
                        <button type="button" class="btn btn-facebook btn-icon-only rounded-circle"
                          :class="setColor"></button>
                        <h6 class="ms-2 mb-0"><a href="javacsript:void(0);" @click="moveToDetails">{{ diary.diary_title
                            }}</a></h6>
                        <span class="ms-2 text-secondary font-weight-normal">{{ diary.written_date }}</span>
                      </div>
                    </a>
                  </ul> -->

                  <!-- <div class="card-group mb-3" v-for="(diary, idx) in diaryData" :key="idx">
                    <div class="card">
                      <div class="card-header">
                        <div class="d-flex align-items-center">
                          <h6>
                            {{ diary.written_date }}-{{ diary.diary_title }}
                          </h6>
                          <button class="btn btn-link text-info ">
                            <i class="material-icons text-lg">edit</i>
                          </button>
                        </div>
                      </div>
                      <div class="card-body text-center">
                        <p class="mb-0">
                          {{ diary.details }}
                        </p>
                      </div>
                      <hr class="dark horizontal my-0">
                      <div class="card-footer d-flex">
                        {{ diary.writer_id }}
                      </div>
                    </div>
                  </div> -->

                </table>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<style>
.setColor {
  background-color: var(--color, white)
}
</style>
