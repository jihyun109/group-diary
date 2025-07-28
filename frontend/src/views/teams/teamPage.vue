<script>
import { mapState, mapActions } from 'vuex';
import UserProfile from '@/components/UserProfile.vue';
import { fetchUserTeams } from '@/api/member.js';
import { fetchTeamDiaryList } from '@/api/teamDiary.js';
import '../../assets/styles.css';
const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export default {
  components: {
    UserProfile,
  },

  async mounted() {
    this.$store.dispatch('fetchStoreData');
    this.fetchData();

    this.teamData = await fetchUserTeams(this.userId);
    this.diaryData = await fetchTeamDiaryList(this.$route.query.team);
  },
  watch: {
    '$route.query.team': async function (newTeam) {
      this.fetchData();
      this.diaryData = await fetchTeamDiaryList(this.$route.query.team);
    },
    teamList(newTeamList) {
      this.teamData = newTeamList;
    },
  },

  created() {
    this.$store.dispatch('fetchStoreData');
  },

  data() {
    return {
      color: '',
      searchWord: '',
      groupNameToCreate: '',
      selectedTeam: '',
      dataList: null,
      dataTypeMap: new Map(),
      diaryData: null,
      teamData: null,
      membersData: null,
      usersData: null,
      userSearchData: [],
      inviteData: null,
      teamMembersData: null,
      usersToInvite: [],
      showMemberList: false,
      str: '',

      isCalendar: false,
      dropdownText: 'list',
      // 사용자가 속한 team의 이름을 받아와서 배열에 저장.
      // team name 클릭하면 team page로 이동. (team 정보를 가지고서.),

      errors: {
        groupNameToCreate: false,
      },
      clickedUserLastName: '',
      clickedUserFirstName: '',
      currentPage: 1,
      itemsPerPage: 7,
    };
  },

  computed: {
    ...mapState(['userId', 'firstName', 'lastName']),
    ...mapState({
      teamList: (state) => state.teamList,
    }),

    filteredUserSearchData() {
      const invitedUserIds = new Set(
        this.usersToInvite.map((user) => user.userId)
      );
      console.log('invitedUserIds:', invitedUserIds);
      return this.userSearchData.filter(
        (user) => user.id !== this.userId && !invitedUserIds.has(user.id)
      );
    },

    sortedDiaryData() {
      return this.diaryData
        .slice()
        .sort((a, b) => new Date(b.written_date) - new Date(a.written_date));
    },

    paginatedDiaries() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.sortedDiaryData.slice(start, end);
    },

    totalPages() {
      return Math.ceil(this.diaryData.length / this.itemsPerPage);
    },
  },

  methods: {
    ...mapActions(['updateTeamList']),

    async changePage(page) {
      if (page > 0 && page <= this.totalPages) {
        this.currentPage = page;
        this.teamData = await fetchUserTeams(this.userId);
      }
    },

    async fetchData() {
      const teamId = this.$route.query.team;
      const menuList = await Promise.all([
        // {
        //   type: 'diaries',
        //   url: `${BASE_URL}/teamDiaries/diaryList/${this.$route.query.team}`,
        // },
        { type: 'teamMembers', url: `${BASE_URL}/members/${teamId}` },
        { type: 'users', url: `${BASE_URL}/users` },
        { type: 'invites', url: `${BASE_URL}/members/invited/${this.userId}` },
      ]);

      const requests = menuList.map(async (dataReq) => {
        const res = await fetch(dataReq.url);
        return res.json();
      });

      this.dataList = await Promise.all(requests);
      this.dataTypeMap = new Map(
        this.dataList.map((data, idx) => [menuList[idx].type, data.data])
      );
      // this.diaryData = this.dataTypeMap.get('diaries');
      // console.log('diaryData: ', this.diaryData);

      this.teamMembersData = this.dataTypeMap.get('teamMembers');
      console.log('teamMembersData: ', this.teamMembersData);
      this.usersData = this.dataTypeMap.get('users');
      this.inviteData = this.dataTypeMap.get('invites');

      this.selectedTeam = this.teamData
        .filter((t) => t.team_id == this.$route.query.team)
        .at(0);
    },

    // 일기 리스트 보기 방식 변경
    setView(text) {
      this.dropdownText = text;
      this.isCalendar = text === 'calendar';
    },

    // 사용자 검색
    async searchUser() {
      try {
        const response = await fetch(
          `${BASE_URL}/users/search/?searchWord=${encodeURIComponent(this.searchWord)}`,
          {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
            },
          }
        );
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
      console.log(diaryId);
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
      console.log('userToInvite[]: ', this.usersToInvite);
    },

    // 초대 그룹에서 삭제
    removeFromInviteGroup(userId) {
      console.log(userId);
      this.usersToInvite = this.usersToInvite.filter(
        (user) => user.userId != userId
      );
      console.log('userToInvite[]: ', this.usersToInvite);
    },

    // usersToInvite 배열 초기화
    resetUsersToInvite() {
      this.usersToInvite = [];
      this.groupNameToCreate = '';
      this.searchWord = '';
      this.userSearchData = [];
      this.usersToInvite = [];
      console.log('userToInvite[]: ', this.userToInvite);
    },

    // create team
    async createTeam() {
      await this.requestCreateTeam();
      await this.inviteUsers();
      alert('팀이 생성되었습니다.');
      this.groupNameToCreate = '';
      this.searchWord = '';
      this.userSearchData = [];
      this.usersToInvite = [];
      const modalElement = document.getElementById('createGroup-form');
      const modal = bootstrap.Modal.getInstance(modalElement);
      modal.hide();

      this.teamData = await fetchUserTeams(this.userId);
      this.fetchData();

    },

    async inviteToTeam() {
      for (let i = 0; i < this.usersToInvite.length; i++) {
        let userId = this.usersToInvite[i].userId;
        await this.requestInviteUser(userId, this.$route.query.team);
      }
      alert('팀에 초대되었습니다.');
      const modalElement = document.getElementById('inviteUser-form');
      const modal = bootstrap.Modal.getInstance(modalElement);
      modal.hide();
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
        const response = await fetch(`${BASE_URL}/teams`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            team_name: this.groupNameToCreate,
            creator_id: this.userId,
          }),
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
        console.log('userId:', userId, ', teamId: ', teamId);
        this.requestInviteUser(userId, teamId);
      }
    },

    // 초대 요청 보내기
    async requestInviteUser(userId, teamId) {
      // 입력 데이터를 객체로 수집
      const inviteData = {
        userId: userId,
        teamId: teamId,
        status: userId === this.userId ? 0 : 1,
        inviterId: this.userId,
      };

      try {
        // 서버로 POST 요청 보내기
        const response = await fetch(`${BASE_URL}/members`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(inviteData),
        });

        if (response.ok) {
          // 요청이 성공하면 성공 메시지 표시
          console.log('초대 성공: ', inviteData);
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
        console.log('store userId: ', this.userId);
        const response = await fetch(
          `${BASE_URL}/teams/findId?teamName=${encodeURIComponent(this.groupNameToCreate)}&creatorId=${this.userId}`,
          {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
            },
            // body: JSON.stringify(groupData)
          }
        );

        console.log('response: ', response);

        if (response.ok) {
          const resjson = await response.json();
          console.log(resjson);
          const teamId = resjson.data;
          console.log('find team id. teamId: ', teamId);
          return teamId;
        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          console.log(`team not founded: ${errorData.message}`);
        }
      } catch (error) {
        // 네트워크 오류 처리
        console.log(
          `team not founded. 네트워크 오류가 발생했습니다: ${error.message}`
        );
      }
    },
    // 클릭시 사용자 정보 업데이트
    updateClickedUserInfo(userId, lastName, firstName) {
      this.clickedUserLastName = lastName;
      this.clickedUserFirstName = firstName;
      this.usersToInvite.push({ userId, lastName, firstName });
    },
    initUsersToInvite() {
      this.usersToInvite = [];
      // console.log("aaa")
    },
    toggleShowMemberList() {
      this.showMemberList = !this.showMemberList;
    },
    teamActiveStyle(id) {
      if (this.selectedTeam.team_id == id) {
        return ' bg-info text-white';
      }
      return '';
    },
    lightenColor(color) {
      // Function to lighten the color
      // Example: lighten by 50% (increase opacity)
      // Assuming color is in hex format, convert to rgba and adjust alpha channel
      const rgbaColor = this.hexToRgba(color, 0.5); // 0.5 means 50% opacity
      return rgbaColor;
    },
    hexToRgba(hex, opacity) {
      // Convert hex color to rgba format with specified opacity
      hex = hex.replace(/^#/, '');
      const rgb = parseInt(hex, 16);
      const r = (rgb >> 16) & 0xff;
      const g = (rgb >> 8) & 0xff;
      const b = rgb & 0xff;
      return `rgba(${r},${g},${b},${opacity})`;
    },
  },
};
</script>

<template>
  <div
    class="card"
    style="max-width: 1250px; height: 800px; background-color: #aeba94"
  >
    <div class="container">
      <p v-if="!dataList">로딩...</p>
      <div v-else>
        <div class="row mt-3">
          <div class="col-2 ms-3 mt-6">
            <!-- create group 버튼 -->
            <button
              type="button"
              class="btn btn-block"
              data-bs-toggle="modal"
              data-bs-target="#createGroup-form"
              style="background-color: #e1dacc"
            >
              create group
            </button>

            <!-- team 목록 -->
            <div class="list-group team-list-scroll" v-if="teamData">
              <a
                @click="moveToTeamPage(team.team_id)"
                href="javacsript:void(0);"
                :class="`list-group-item list-group-item-action ${teamActiveStyle(team.team_id)}`"
                v-for="(team, idx) in teamData"
                :key="idx"
              >
                {{ team.team_name }}
              </a>
            </div>
          </div>

          <!-- 화면 왼쪽 -->
          <div class="col-8 me-3 ms-5">
            <div class="row mt-3">
              <div class="d-flex align-items-center">
                <h1>
                  {{ selectedTeam.team_name }}
                </h1>
                <button
                  class="btn ms-5"
                  data-bs-toggle="modal"
                  data-bs-target="#inviteUser-form"
                  id="searchBtn"
                  style="background-color: #e3ede6"
                >
                  멤버 초대
                </button>
              </div>

              <div class="d-flex align-items-center ms-3 mt-2">
                <button
                  @click="toggleShowMemberList(idx)"
                  class="btn ms-1"
                  style="background-color: #638589; color: #ffffff"
                >
                  {{ showMemberList ? 'member hide' : 'member show' }}
                </button>

                <div v-if="showMemberList" class="mb-3 ms-3 mt-2">
                  <span
                    v-for="(member, idx) in teamMembersData"
                    :key="idx"
                    class="ms-1 d-inline-flex badge align-items-center p-1 pe-2 text-success-emphasis border border-success-subtle rounded-pill"
                    :style="{
                      backgroundColor: lightenColor(member.color),
                      alignItems: 'center',
                    }"
                  >
                    <UserProfile
                      class="rounded-circle me-1"
                      :fontSize="10"
                      :width="24"
                      :height="24"
                      :color="member.color"
                      :firstName="member.name"
                    ></UserProfile>
                    <span>{{ member.name }}</span>
                  </span>
                </div>
              </div>
            </div>

            <!-- 일기 리스트 -->
            <!-- 달력 보기 방식 -->
            <h1 v-if="isCalendar">calendar</h1>
            <!-- 리스트 보기 방식 -->
            <div v-else>
              <div class="card rounded-card mt-2">
                <div class="card-body">
                  <div class="table-responsive">
                    <div
                      class="d-flex align-items-center justify-content-between"
                      style="margin: 10px 0"
                    >
                      <h4 class="font-weight-bold" style="margin-left: 30px">
                        Diaries
                      </h4>
                      <a
                        href="/writeDiary"
                        style="margin: 10px 30px -10px 10px"
                      >
                        <img
                          src="../../assets/img/write.png"
                          style="height: 30px; margin-right: 10px"
                        />
                        <strong>Write diary</strong>
                      </a>
                    </div>

                    <table
                      class="table align-items-center mb-0 table-background rounded-table"
                    >
                      <!-- 표 헤더 -->
                      <thead>
                        <tr>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
                          >
                            Author
                          </th>
                          <th
                            class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Title
                          </th>
                          <th
                            class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
                          >
                            Date
                          </th>
                        </tr>
                      </thead>
                      <!-- 표 body -->
                      <tbody
                        v-for="(diary, idx) in paginatedDiaries"
                        :key="idx"
                      >
                        <!-- 한 행 -->
                        <tr>
                          <!-- author -->
                          <td>
                            <div class="d-flex px-2 py-1">
                              <UserProfile
                                :color="diary.color"
                                :firstName="diary.first_name"
                                :lastName="diary.last_name"
                              >
                              </UserProfile>
                            </div>
                          </td>
                          <!-- title -->
                          <td>
                            <h6 class="mb-0">
                              <a
                                href="javacsript:void(0);"
                                @click="moveToDetails(diary.id)"
                                >{{ diary.diary_title }}</a
                              >
                            </h6>
                          </td>
                          <!-- Date -->
                          <td class="align-middle text-center">
                            <span class="text-secondary font-weight-normal">{{
                              diary.written_date
                            }}</span>
                          </td>
                        </tr>
                      </tbody>
                    </table>

                    <!-- Pagination -->
                    <nav class="mt-4" aria-label="Page navigation example">
                      <ul class="pagination justify-content-center">
                        <li
                          :class="[
                            'page-item',
                            { disabled: currentPage === 1 },
                          ]"
                        >
                          <a
                            class="page-link"
                            href="javascript:;"
                            @click="changePage(currentPage - 1)"
                            tabindex="-1"
                          >
                            <i class="fa fa-angle-left"></i>
                            <span class="sr-only">Previous</span>
                          </a>
                        </li>
                        <li
                          v-for="page in totalPages"
                          :key="page"
                          :class="[
                            'page-item',
                            { active: page === currentPage },
                          ]"
                        >
                          <a
                            class="page-link"
                            href="javascript:;"
                            @click="changePage(page)"
                            >{{ page }}</a
                          >
                        </li>
                        <li
                          :class="[
                            'page-item',
                            { disabled: currentPage === totalPages },
                          ]"
                        >
                          <a
                            class="page-link"
                            href="javascript:;"
                            @click="changePage(currentPage + 1)"
                          >
                            <i class="fa fa-angle-right"></i>
                            <span class="sr-only">Next</span>
                          </a>
                        </li>
                      </ul>
                    </nav>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- createGroup modal -->
      <div
        class="modal fade"
        id="createGroup-form"
        tabindex="-1"
        role="dialog"
        aria-labelledby="createGroup-form"
        aria-hidden="true"
        data-bs-backdrop="static"
      >
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <!-- 헤더 -->
            <div class="modal-header">
              <h5
                class="modal-title"
                @click="initUsersToInvite"
                id="modal-title-notification"
              >
                Create new group
              </h5>
            </div>
            <!-- body -->
            <div class="modal-body p-0">
              <div class="card card-plain">
                <div class="card-body">
                  <form role="form text-left d-flex">
                    <label>Group name</label>
                    <div class="input-group input-group-outline mb-3">
                      <input
                        v-model="groupNameToCreate"
                        placeholder="Group Name"
                        type="text"
                        class="form-control"
                        :class="{ 'is-invalid': errors.groupNameToCreate }"
                      />
                    </div>

                    <label>Add friends to this group</label>

                    <div id="recipient_input_list">
                      <span
                        v-for="(user, idx) in usersToInvite"
                        :key="idx"
                        class="me-1 badge align-items-center p-1 pe-2 text-success-emphasis bg-success-subtle border border-success-subtle rounded-pill d-inline-flex align-items-center"
                      >
                        <span style="margin-left: 10px"
                          >{{ user.lastName }} {{ user.firstName }}</span
                        >
                        <span class="vr mx-2"></span>
                        <a
                          href="javacsript:void(0);"
                          @click="removeFromInviteGroup(user.userId)"
                        >
                          <span class="material-icons opacity-6 me-2 text-md"
                            >cancel</span
                          >
                        </a>
                      </span>
                    </div>

                    <form @submit.prevent="searchUser">
                      <div class="input-group input-group-outline mb-3 mt-2">
                        <div class="col-8">
                          <input
                            v-model="searchWord"
                            class="form-control me-2"
                            type="text"
                            placeholder="Search"
                          />
                        </div>
                        <div class="col-4 ps-0">
                          <button
                            @click="searchUser"
                            class="btn ms-3"
                            style="background-color: #4f684e; color: #ffffff"
                            id="searchBtn"
                          >
                            Search
                          </button>
                        </div>
                      </div>

                      <div
                        v-if="filteredUserSearchData.length === 0"
                        class="list-group"
                      >
                        <p>no such user</p>
                      </div>
                      <div v-else class="list-group">
                        <a
                          v-for="(user, idx) in filteredUserSearchData"
                          :key="idx"
                          @click="
                            addToInviteGroup(
                              user.id,
                              user.lastName,
                              user.firstName
                            )
                          "
                          href="javacsript:void(0);"
                          class="list-group-item list-group-item-action"
                        >
                          {{ user.lastName }} {{ user.firstName }}
                        </a>
                      </div>
                    </form>
                  </form>
                </div>
              </div>
            </div>

            <!-- modal footer -->
            <div class="modal-footer">
              <button
                @click="createTeam"
                type="button"
                class="btn"
                style="background-color: #718e71; color: #ffffff"
              >
                Create
              </button>
              <button
                @click="resetUsersToInvite"
                type="button"
                class="btn"
                data-bs-dismiss="modal"
                style="background-color: #e5d9c4; color: #000000"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>

      <!--inviteUser modal -->
      <div
        class="modal fade"
        id="inviteUser-form"
        tabindex="-1"
        role="dialog"
        aria-labelledby="inviteUser-form"
        aria-hidden="true"
        data-bs-backdrop="static"
      >
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <!-- 헤더 -->
            <div class="modal-header">
              <h5
                class="modal-title"
                @click="initUsersToInvite"
                id="modal-title-notification"
              >
                멤버 추가
              </h5>
            </div>
            <!-- body -->
            <div class="modal-body p-0">
              <div class="card card-plain">
                <div class="card-body">
                  <form role="form text-left d-flex">
                    <label>Add friends to this group</label>
                    <form @submit.prevent="searchUser">
                      <div class="input-group input-group-outline">
                        <div class="col-8">
                          <input
                            v-model="searchWord"
                            class="form-control me-2"
                            type="text"
                            placeholder="Search"
                          />
                        </div>
                        <div class="col-4 ps-0">
                          <button
                            @click="searchUser"
                            class="btn ms-3"
                            style="background-color: #4f684e; color: #ffffff"
                            id="searchBtn"
                          >
                            Search
                          </button>
                        </div>
                      </div>
                    </form>

                    <div id="recipient_input_list" class="d-flex mb-3">
                      <span
                        v-for="(user, idx) in usersToInvite"
                        :key="idx"
                        class="badge align-items-center p-1 pe-2 text-success-emphasis bg-success-subtle border border-success-subtle rounded-pill"
                      >
                        {{ user.lastName }} {{ user.firstName }}
                        <span class="vr mx-2"></span>
                        <a
                          href="javacsript:void(0);"
                          @click="removeFromInviteGroup(user.userId)"
                        >
                          <span class="material-icons opacity-6 me-2 text-md"
                            >cancel</span
                          >
                        </a>
                      </span>
                    </div>

                    <div
                      v-if="filteredUserSearchData.length === 0"
                      class="list-group"
                    >
                      <p>no such user</p>
                    </div>
                    <div v-else class="list-group">
                      <a
                        v-for="(user, idx) in filteredUserSearchData"
                        :key="idx"
                        @click="
                          updateClickedUserInfo(
                            user.id,
                            user.lastName,
                            user.firstName
                          )
                        "
                        href="javacsript:void(0);"
                        class="list-group-item list-group-item-action"
                      >
                        {{ user.lastName }} {{ user.firstName }}
                      </a>
                    </div>
                    <!-- </form> -->
                  </form>
                </div>
              </div>
            </div>

            <!-- modal footer -->
            <div class="modal-footer">
              <button
                @click="inviteToTeam"
                type="button"
                class="btn"
                style="background-color: #718e71; color: #ffffff"
              >
                invite
              </button>

              <button
                @click="resetUsersToInvite"
                type="button"
                class="btn"
                data-bs-dismiss="modal"
                style="background-color: #e5d9c4; color: #000000"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.setColor {
  background-color: var(--color, white);
}

.team-list-scroll {
  max-height: 400px;
  /* 원하는 높이 설정 */
  overflow-y: auto;
  /* 세로 스크롤바 추가 */
}

.rounded-card {
  max-width: 800px;
  /* 일기 리스트의 폭 줄이기 */
  margin: 0 auto;
  /* 가운데 정렬 */
  margin-top: 50px;
  /* 카드 위에 공백 추가 */
}

.table-responsive {
  max-width: 800px;
  /* 테이블의 폭을 줄이기 */
  margin: 0 auto;
  /* 가운데 정렬 */
}

/* 활성화된 페이지 항목 색상 변경 */
.pagination .page-item.active .page-link {
  background-color: #e1dacc;
  /* 원하는 배경색 */
  border-color: #e1dacc;
  /* 원하는 테두리 색 */
  color: #ffffff;
  /* 텍스트 색 */
}
</style>
