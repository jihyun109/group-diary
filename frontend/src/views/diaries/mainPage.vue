<template>
  <div class="container">
    <p v-if="!dataList">로딩...</p>
    <div v-else>
      <div class="row mt-3">
        <div class="col-2 ms-3" style="margin: 10px 0;">
          <!-- create group 버튼 -->
          <button type="button" class="btn btn-block" data-bs-toggle="modal" data-bs-target="#createGroup-form"
            style="background-color: #E1DACC;">create group
          </button>
          <!-- team 목록 -->
          <ul class="list-group mt-2 team-list-scroll" v-if="teamData">
            <a @click="moveToTeamPage(team.team_id)" href="javascript:void(0);"
              class="list-group-item list-group-item-action" v-for="(team, idx) in teamData" :key="idx">
              {{ team.team_name }}
            </a>
          </ul>
        </div>

        <div class="col-8 me-3">
          <!-- 일기 리스트 -->
          <!-- 달력 보기 방식 -->
          <h1 v-if="isCalendar">calendar</h1>
          <!-- 리스트 보기 방식 -->
          <div v-else>
            <div class="card rounded-card">
              <div class="card-body">
                <div class="table-responsive">
                  <div class="d-flex align-items-center justify-content-between" style="margin: 10px 0;">
                    <h4 class="font-weight-bold" style="margin-left: 30px;">Diaries</h4>
                    <a href="/writeDiary" style="margin: 10px 30px -10px 10px;">
                      <img src="../../assets/img/write.png" style="height: 30px; margin-right: 10px;">
                      <strong>Write diary</strong>
                    </a>
                  </div>

                  <table class="table align-items-center mb-0 table-background rounded-table">
                    <!-- 표 헤더 -->
                    <thead>
                      <tr>
                        <th class="author-column text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                          Author</th>
                        <th
                          class="title-column text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                          Title</th>
                        <th
                          class="date-column text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                          Date
                        </th>
                      </tr>
                    </thead>
                    <!-- 표 body -->
                    <tbody>
                      <!-- 한 행 -->
                      <tr v-for="(diary, idx) in paginatedDiaries" :key="idx">
                        <!-- author -->
                        <td class="author-column">
                          <div class="d-flex px-2 py-1">
                            <UserProfile :color="diary.color" :firstName="diary.first_name" :lastName="diary.last_name">
                            </UserProfile>
                          </div>
                        </td>
                        <!-- title -->
                        <td>
                          <h6 class="mb-0 title-column">
                            <a href="javascript:void(0);" @click="moveToDetails(diary.id)">{{ diary.diary_title }}</a>
                          </h6>
                        </td>
                        <!-- Date -->
                        <td class="date-column align-middle text-center">
                          <span class="text-secondary font-weight-normal">{{ diary.written_date }}</span>
                        </td>
                      </tr>
                    </tbody>
                  </table>

                  <!-- Pagination -->
                  <nav class="mt-4" aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                      <li :class="['page-item', { disabled: currentPage === 1 }]">
                        <a class="page-link" href="javascript:;" @click="changePage(currentPage - 1)" tabindex="-1">
                          <i class="fa fa-angle-left"></i>
                          <span class="sr-only">Previous</span>
                        </a>
                      </li>
                      <li v-for="page in totalPages" :key="page"
                        :class="['page-item', { active: page === currentPage }]">
                        <a class="page-link" href="javascript:;" @click="changePage(page)">{{ page }}</a>
                      </li>
                      <li :class="['page-item', { disabled: currentPage === totalPages }]">
                        <a class="page-link" href="javascript:;" @click="changePage(currentPage + 1)">
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
    <!-- modal -->
    <div class="modal fade" id="createGroup-form" tabindex="-1" role="dialog" aria-labelledby="createGroup-form"
      aria-hidden="true" data-bs-backdrop="static">
      <div class="modal-dialog modal-dialog-centered " role="document">
        <div class="modal-content">
          <!-- 헤더 -->
          <div class="modal-header">
            <h5 class="modal-title" id="modal-title-notification">Create new group</h5>
          </div>

          <!-- body -->
          <div class="modal-body p-0">
            <div class="card card-plain rounded-card">
              <div class="card-body">
                <form role="form text-left d-flex">
                  <label>Group name</label>
                  <div class="input-group input-group-outline mb-3">
                    <input v-model="groupNameToCreate" type="text" class="form-control"
                      :class="{ 'is-invalid': errors.groupNameToCreate }" placeholder="Group Name">
                  </div>

                  <label>Add friends to this group</label>
                  <div id="recipient_input_list">
                    <span v-for="(user, idx) in usersToInvite" :key="idx"
                      class="
                      me-1 badge align-items-center p-1 pe-2 text-success-emphasis bg-success-subtle border border-success-subtle rounded-pill d-inline-flex align-items-center">
                      <span style="margin-left: 10px;">{{ user.lastName }} {{ user.firstName }}</span>
                      <span class="vr mx-2"></span>
                      <a href="javacsript:void(0);" @click="removeFromInviteGroup(user.userId)">
                        <span class="material-icons opacity-6 me-2 text-md">cancel</span>
                      </a>
                    </span>
                  </div>

                  <form @submit.prevent="searchUser">
                    <div class="input-group input-group-outline mb-3 mt-2">
                      <div class="col-8">
                        <input v-model="searchWord" class="form-control me-2" type="text" placeholder="Search">
                      </div>
                      <div class="col-4 ps-0">
                        <button @click="searchUser" class="btn btn-outline-success ms-3" id="searchBtn">Search</button>
                      </div>
                    </div>
                  </form>
                  <div v-if="filteredUserSearchData.length === 0" class="list-group">
                    <p>no such user</p>
                  </div>
                  <div v-else class="list-group">
                    <a v-for="(user, idx) in filteredUserSearchData" :key="idx"
                      @click="addToInviteGroup(user.id, user.last_name, user.first_name)" href="javascript:void(0);"
                      class="list-group-item list-group-item-action">
                      {{ user.last_name }} {{ user.first_name }}
                    </a>
                  </div>
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
  </div>
</template>

<script>
import { mapState } from 'vuex';
// import { mapGetters } from 'vuex';
import UserProfile from '@/components/UserProfile.vue'
import '../../assets/styles.css';

export default {
  components: {
    UserProfile
  },

  mounted() {
    this.$store.dispatch('fetchStoreData');
    this.fetchData();
  },

  data() {
    return {
      currentPage: 1,
      itemsPerPage: 7,
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
      errors: {
        groupNameToCreate: false
      }
    }
  },

  created() {
    this.$store.dispatch('fetchStoreData');
  },

  computed: {
    ...mapState(['userId', 'firstName', 'lastName']),
    ...mapState({
      teamList: state => state.teamList
    }),

    sortedDiaryData() {
      return this.diaryData.slice().sort((a, b) => new Date(b.written_date) - new Date(a.written_date));
    },

    filteredUserSearchData() {
      const invitedUserIds = new Set(this.usersToInvite.map(user => user.userId));
      return this.userSearchData.filter(user => user.id !== this.userId && !invitedUserIds.has(user.id));
    },

    paginatedDiaries() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.sortedDiaryData.slice(start, end);
    },

    totalPages() {
      return Math.ceil(this.diaryData.length / this.itemsPerPage);
    }
  },

  watch: {
    teamList(newTeamList) {
      this.teamData = newTeamList;
    }
  },

  methods: {
    async fetchData() {
      const menuList = await Promise.all([
        { type: 'diaries', url: `http://localhost:8080/diaries/all/${this.userId}` },
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
      this.diaryData = this.dataTypeMap.get('diaries')
      this.teamData = this.dataTypeMap.get('teams')
      this.membersData = this.dataTypeMap.get('members')
      this.usersData = this.dataTypeMap.get('users')
      this.inviteData = this.dataTypeMap.get('invites')
    },

    setView(text) {
      this.dropdownText = text
      this.isCalendar = text === 'calendar'
    },

    async requestAcceptInvite(invite) {
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
          this.inviteData = this.inviteData.filter(invite => invite.id !== invite.id);
          this.fetchData()
        } else {
          console.error('Error accepting invite');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

    async requestRefuseInvite(inviteId) {
      try {
        const response = await fetch(`http://localhost:8080/members/${inviteId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (response.ok) {
          this.inviteData = this.inviteData.filter(invite => invite.id !== inviteId);
        } else {
          console.error('Error accepting invite');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

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
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

    logOut() {
      this.$store.commit('logOut');
      this.$router.push({ path: 'logIn' });
    },

    moveToDetails(diaryId) {
      this.$router.push({ path: 'diaryDetail', query: { diary: diaryId } });
    },

    moveToTeamPage(teamId) {
      this.$router.push({ path: 'teamPage', query: { team: teamId } });
    },

    addToInviteGroup(userId, lastName, firstName) {
      this.usersToInvite.push({ userId, lastName, firstName });
    },

    removeFromInviteGroup(userId) {
      this.usersToInvite = this.usersToInvite.filter(user => user.userId != userId);
    },

    resetUsersToInvite() {
      this.usersToInvite = [];
      this.groupNameToCreate = '';
      this.searchWord = '';
      this.userSearchData = [];
      this.usersToInvite = [];
    },

    async createTeam() {
      await this.requestCreateTeam();
      await this.inviteUsers();
      alert('팀이 생성되었습니다.');
      this.groupNameToCreate = '';
      this.searchWord = '';
      this.userSearchData = [];
      this.usersToInvite = [];

      // 모달 닫기
      const modalElement = document.getElementById('createGroup-form');
      const modal = bootstrap.Modal.getInstance(modalElement); // Bootstrap 5 method
      if (modal) {
        modal.hide();
      }

      this.fetchData();
    },

    async requestCreateTeam() {
      this.errors.groupNameToCreate = !this.groupNameToCreate;

      if (this.errors.groupNameToCreate) {
        alert('그룹 이름을 입력해 주세요');
        return;
      }

      try {
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
          const responseData = await response.json();
        } else {
          const errorData = await response.json();
          alert(`오류가 발생했습니다: ${errorData.message}`);
        }
      } catch (error) {
        alert(`네트워크 오류가 발생했습니다: ${error.message}`);
      }
    },

    async inviteUsers() {
      const teamData = await this.requestTeamId();
      const teamId = teamData[0].id;

      this.requestInviteUser(this.userId, teamId);

      for (let i = 0; i < this.usersToInvite.length; i++) {
        let userId = this.usersToInvite[i].userId;
        this.requestInviteUser(userId, teamId);
      }
    },

    async requestInviteUser(userId, teamId) {
      const inviteData = {
        user_id: userId,
        team_id: teamId,
        status: userId === this.userId ? 0 : 1,
        inviter_id: this.userId
      };

      try {
        const response = await fetch('http://localhost:8080/members/', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(inviteData)
        });

        if (response.ok) {
          console.log("초대 성공: ", inviteData);
        } else {
          const errorData = await response.json();
          console.log(`오류가 발생했습니다: ${errorData.message}`);
        }
      } catch (error) {
        alert(`네트워크 오류가 발생했습니다: ${error.message}`);
      }
    },

    async requestTeamId() {
      try {
        const response = await fetch(`http://localhost:8080/teams/findId?teamName=${encodeURIComponent(this.groupNameToCreate)}&creatorId=${this.userId}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        });

        if (response.ok) {
          const resjson = await response.json();
          const teamId = resjson.data;
          return teamId;
        } else {
          const errorData = await response.json();
          console.log(`team not founded: ${errorData.message}`)
        }
      } catch (error) {
        console.log(`team not founded. 네트워크 오류가 발생했습니다: ${error.message}`);
      }
    },

    changePage(page) {
      if (page > 0 && page <= this.totalPages) {
      this.currentPage = page;
      this.fetchData();
    }
    }
  }
}
</script>

<style>
body {
  background-color: #afbda4;
}

.author-column {
  width: 50px;
  /* author 열의 가로 폭 줄이기 */
}

.title-column {
  width: 100px;
  /* title 열의 가로 폭 줄이기 */
}

.date-column {
  width: 150px;
  /* date 열의 가로 폭 줄이기 */
}

.card {
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

.mt-4 {
  margin-top: 1rem !important;
  /* nav를 일기 리스트에서 더 아래로 위치시키기 */
}

.rounded-card {
  border-radius: 15px;
}

.rounded-table {
  border-collapse: separate;
  border-spacing: 0;
  border-radius: 15px;
  overflow: hidden;
  padding-left: 15px;
  /* 좌측 여백 추가 */
  padding-right: 15px;
  /* 우측 여백 추가 */
}

.table-background {
  /* background-color: #f7f4f0; */
  padding-left: 15px;
  /* 좌측 여백 추가 */
  padding-right: 15px;
  /* 우측 여백 추가 */
}

.team-list-scroll {
  max-height: 400px;
  /* 원하는 높이 설정 */
  overflow-y: auto;
  /* 세로 스크롤바 추가 */
}

/* 활성화된 페이지 항목 색상 변경 */
.pagination .page-item.active .page-link {
  background-color: #E1DACC; /* 원하는 배경색 */
  border-color: #E1DACC; /* 원하는 테두리 색 */
  color: #ffffff; /* 텍스트 색 */
}
</style>
