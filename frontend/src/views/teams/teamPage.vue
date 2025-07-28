<script>
import { mapState, mapActions } from 'vuex';
import UserProfile from '@/components/UserProfile.vue';
import { fetchUserTeams, fetchTeamMembers } from '@/api/member.js';
import { fetchTeamDiaryList } from '@/api/teamDiary.js';
import CreateTeamModal from '@/components/CreateTeamModal.vue';
import InviteUserModal from '@/components/InviteUserModal.vue';
import '../../assets/styles.css';
const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export default {
  components: {
    UserProfile,
    CreateTeamModal,
    InviteUserModal,
  },

  async mounted() {
    this.$store.dispatch('fetchStoreData');
    // this.fetchData();

    await this.initializeData();
  },
  watch: {
    '$route.query.team': async function (newTeam) {
      // this.fetchData();
      this.diaryData = await fetchTeamDiaryList(this.$route.query.team);
      this.selectedTeam = this.teamData.find(
        (t) => t.team_id == this.$route.query.team
      );
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
      selectedTeam: '',
      diaryData: [],
      teamData: null,
      userSearchData: [],
      teamMembersData: null,
      usersToInvite: [],
      showMemberList: false,

      isCalendar: false,
      dropdownText: 'list',

      currentPage: 1,
      itemsPerPage: 7,
      isLoading: false,
    };
  },

  computed: {
    ...mapState(['userId', 'firstName', 'lastName']),
    ...mapState({
      teamList: (state) => state.teamList,
    }),

    sortedDiaryData() {
      if (!this.diaryData) return [];
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
      if (!this.diaryData) return 0;
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

    async initializeData() {
      this.isLoading = true;

      this.teamData = await fetchUserTeams(this.userId);
      this.diaryData = await fetchTeamDiaryList(this.$route.query.team);
      this.teamMembersData = await fetchTeamMembers(this.$route.query.team);

      this.selectedTeam = this.teamData.find(
        (t) => t.team_id == this.$route.query.team
      );

      this.isLoading = false;
    },

    // 일기 리스트 보기 방식 변경
    setView(text) {
      this.dropdownText = text;
      this.isCalendar = text === 'calendar';
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

    // usersToInvite 배열 초기화
    resetUsersToInvite() {
      this.usersToInvite = [];
    },

    // 팀 생성 완료 후 처리
    async handleTeamCreated() {
      this.teamData = await fetchUserTeams(this.userId);
      // this.fetchData();
    },

    // 멤버 초대 완료 후 처리
    async handleUsersInvited() {
      this.teamMembersData = await fetchTeamMembers(this.$route.query.team);
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
      <p v-if="!diaryData">로딩...</p>
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
      <CreateTeamModal @team-created="handleTeamCreated" />

      <!--inviteUser modal -->
      <InviteUserModal :teamId="$route.query.team" @users-invited="handleUsersInvited" />
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
