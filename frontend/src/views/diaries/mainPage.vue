<template>
  <div
    class="card"
    style="max-width: 1250px; height: 800px; background-color: #aeba94"
  >
    <div class="container">
      <p v-if="!diaryData">로딩...</p>
      <div v-else>
        <div class="row mt-3">
          <div class="col-2 ms-3 mt-6" style="margin: 10px 0">
            <!-- create group 버튼 -->
            <button
              type="button"
              class="btn btn-block"
              data-bs-toggle="modal"
              data-bs-target="#createGroup-form"
              style="background-color: #e0d4be"
            >
              create group
            </button>
            <!-- team 목록 -->
            <ul class="list-group team-list-scroll" v-if="teamData">
              <a
                @click="moveToTeamPage(team.team_id)"
                class="list-group-item list-group-item-action"
                v-for="(team, idx) in teamData"
                :key="idx"
                style="cursor: pointer;"
              >
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
                            class="author-column text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
                          >
                            Author
                          </th>
                          <th
                            class="title-column text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2"
                          >
                            Title
                          </th>
                          <th
                            class="date-column text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7"
                          >
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
                              <UserProfile
                                :color="diary.color"
                                :firstName="diary.firstName"
                                :lastName="diary.lastName"
                              >
                              </UserProfile>
                            </div>
                          </td>
                          <!-- title -->
                          <td>
                            <h6 class="mb-0 title-column">
                              <a
                                @click="moveToDetails(diary.id)"
                                style="cursor: pointer; text-decoration: none; color: inherit;"
                                >{{ diary.diaryTitle }}</a
                              >
                            </h6>
                          </td>
                          <!-- Date -->
                          <td class="date-column align-middle text-center">
                            <span class="text-secondary font-weight-normal">{{
                              diary.writtenDate
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
      <!-- modal -->
      <CreateTeamModal @team-created="handleTeamCreated" />
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import UserProfile from '@/components/UserProfile.vue';
import CreateTeamModal from '@/components/CreateTeamModal.vue';
import '../../assets/styles.css';

import { fetchAllDiaries } from '@/api/diary.js';
import { fetchUserTeams } from '@/api/member.js';
import { fetchUserInvites } from '@/api/member.js';

export default {
  components: {
    UserProfile,
    CreateTeamModal,
  },

  async mounted() {
    this.$store.dispatch('fetchStoreData');

    this.diaryData = await fetchAllDiaries(this.userId);
    this.teamData = await fetchUserTeams(this.userId);
    this.inviteData = await fetchUserInvites(this.userId);
  },

  data() {
    return {
      currentPage: 1,
      itemsPerPage: 7,
      color: '',
      dataList: null,
      dataTypeMap: new Map(),
      diaryData: null,
      teamData: null,
      usersData: null,
      inviteData: null,
      str: '',
      isCalendar: false,
      dropdownText: 'list',
    };
  },

  created() {
    this.$store.dispatch('fetchStoreData');
  },

  computed: {
    ...mapState(['userId', 'firstName', 'lastName']),
    ...mapState({
      teamList: (state) => state.teamList,
    }),

    sortedDiaryData() {
      return this.diaryData
        .slice()
        .sort((a, b) => new Date(b.writtenDate) - new Date(a.writtenDate));
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

  watch: {
    teamList(newTeamList) {
      this.teamData = newTeamList;
    },
  },

  methods: {
    ...mapActions(['updateTeamList']),

    async updateTeamListLocal() {
      this.teamData = await fetchUserTeams(this.userId);
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

    changePage(page) {
      if (page > 0 && page <= this.totalPages) {
        this.currentPage = page;
        this.fetchData();
      }
    },

    async handleTeamCreated() {
      // Store에서 팀 목록 갱신
      await this.updateTeamList();
      // 기존 데이터도 갱신
      await this.fetchData();
    },

    async fetchData() {
      this.diaryData = await fetchAllDiaries(this.userId);
      this.inviteData = await fetchUserInvites(this.userId);
    },
  },
};
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
}

.table-responsive {
  max-width: 800px;
  /* 테이블의 폭을 줄이기 */
  margin: 0 auto;
  /* 가운데 정렬 */
}

.mt-4 {
  margin-top: 1rem;
  /* nav를 일기 리스트에서 더 아래로 위치시키기 */
}

.rounded-card {
  border-radius: 15px;
  margin-top: 50px;
  /* 카드 위에 공백 추가 */
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
  background-color: #e0d4be; /* 원하는 배경색 */
  border-color: #e0d4be; /* 원하는 테두리 색 */
  color: #ffffff; /* 텍스트 색 */
}
</style>
