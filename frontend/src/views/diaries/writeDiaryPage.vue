<script>
import { mapState } from 'vuex';
import cancelModal from '@/components/cancelModal.vue';
import '../../assets/styles.css?v=1.0';
import { fetchUserTeams } from '@/api/member.js';
import { writeDiary, updateDiary, fetchDiaryDetail } from '@/api/diary.js';
import { shareDiary, fetchSharedTeams, cancelShareDiary } from '@/api/teamDiary.js';

export default {
  async mounted() {
    this.updateNeedUpdate();
    if (this.needUpdate) {
      this.loadDiaryDetail(this.$route.query.diaryId);
      this.loadSharedTeams();
    }
    this.teamData = await fetchUserTeams(this.userId);
  },
  components: {
    cancelModal,
  },
  props: {
    diaryData: Object,
  },
  data() {
    return {
      diaryModel: {
        id: '',
        writtenDate: '',
        diaryTitle: '',
        details: '',
      },
      diaryId: this.$route.query.diaryId,

      cancelMessage: '일기 작성',

      teamListToShare: [], // 일기를 공유할 팀들의 id 저장 리스트
      teamData: [],
      editedTeamList: [], // 수정된 팀 리스트
      addedTeamList: [],
      deletedTeamList: [],
      needUpdate: false,
    };
  },

  computed: {
    ...mapState(['userId', 'firstName', 'lastName']),

    filteredTeamData() {
      if (!this.needUpdate) {
        const teamIdToShare = new Set(
          this.teamListToShare.map((team) => team.id)
        );
        return this.teamData.filter((team) => !teamIdToShare.has(team.team_id));
      } else {
        const teamIdToShare = new Set(
          this.editedTeamList.map((team) => team.id)
        );
        return this.teamData.filter((team) => !teamIdToShare.has(team.team_id));
      }
    },
  },

  methods: {
    updateNeedUpdate() {
      this.needUpdate = !!this.$route.query.diaryId;
    },

    async writeOrUpdateDiary() {
      await this.requestPostOrUpdateDiary();

      // 일기 작성 페이지이면
      if (!this.needUpdate) {
        this.$router.go(-1);
        alert('일기 작성 완료');
      } else {
        // 일기 수정 페이지
        await this.requestEditedTeamList();
        this.$router.go(-1);
        alert('일기 수정 완료');
      }
    },

    async requestPostOrUpdateDiary() {
      this.diaryModel.writerId = this.userId;

      if (this.needUpdate) {
        await updateDiary(this.diaryModel.id, this.diaryModel);
      } else {
        const sharedTeamList = this.teamListToShare.map((team) => team.id);

        try {
          const requestData = {
            writerId: this.userId,
            writtenDate: this.diaryModel.writtenDate,
            title: this.diaryModel.diaryTitle,
            details: this.diaryModel.details,
            sharedTeamList: sharedTeamList,
          };

          await writeDiary(requestData);
        } catch (error) {
          console.error('일기 작성 실패:', error);
        }
      }
    },

    async loadDiaryDetail() {
      try {
        this.diaryModel = await fetchDiaryDetail(this.diaryId);
        this.editedTeamList = this.deepCopyAndRename(
          this.diaryModel.sharedTeamList
        );
      } catch (error) {
        console.error('일기 상세 정보 로드 실패:', error);
        alert(`일기 정보를 불러오지 못했습니다: ${error.message}`);
      }
    },

    formattedDate(diary) {
      if (diary.writtenDate) {
        const date = diary.writtenDate;
        return `Written Date: ${date.slice(0, 2)}-${date.slice(
          2,
          4
        )}-${date.slice(4)}`;
      }
      return '';
    },

    // 공유할 팀 리스트에 추가
    addToTeamListToShare(teamId, teamName) {
      if (!this.needUpdate) {
        this.teamListToShare.push({ id: teamId, team_name: teamName });
      } else {
        this.editedTeamList.push({ id: teamId, team_name: teamName });
      }
    },

    removeFromTeamListToShare(teamId) {
      if (!this.needUpdate) {
        this.teamListToShare = this.teamListToShare.filter(
          (team) => team.id != teamId
        );
      } else {
        {
          this.editedTeamList = this.editedTeamList.filter(
            (team) => team.id != teamId
          );
        }
      }
    },
    
    async loadSharedTeams() {
      try {
        this.sharedTeams = await fetchSharedTeams(this.diaryId);
      } catch (error) {
        console.error('공유된 팀 목록 로드 실패:', error);
        alert(`공유된 팀 목록을 불러오지 못했습니다: ${error.message}`);
      }
    },

    // 수정된 팀 리스트 요청 보내기 (post/delete)
    async requestEditedTeamList() {
      // 추가되거나 삭제된 team 찾기.
      const findAddedTeam = (arr1, arr2) => {
        const arr1Ids = arr1.map((obj) => obj.id);
        return arr2.filter((obj) => !arr1Ids.includes(obj.id));
      };

      const findDeletedTeamList = (arr1, arr2) => {
        const arr2Ids = arr2.map((obj) => obj.id);
        return arr1.filter((obj) => !arr2Ids.includes(obj.id));
      };

      this.addedTeamList = findAddedTeam(
        this.diaryModel.sharedTeamList,
        this.editedTeamList
      );
      this.deletedTeamList = findDeletedTeamList(
        this.diaryModel.sharedTeamList,
        this.editedTeamList
      );

      // 추가된 팀 공유 요청 보내기
      for (const team of this.addedTeamList) {
        await shareDiary(this.diaryId, team.id);
      }

      // 삭제된 팀 delete 요청 보내기
      for (let i = 0; i < this.deletedTeamList.length; i++) {
        await cancelShareDiary(this.diaryId, this.deletedTeamList[i].id);
      }
    },

    deepCopyAndRename(obj) {
      const renameMap = {
        teamId: 'id',
        teamName: 'team_name',
      };
      if (obj === null || typeof obj !== 'object') {
        return obj;
      }

      if (Array.isArray(obj)) {
        return obj.map((item) => this.deepCopyAndRename(item, renameMap));
      }

      const objCopy = {};
      for (const key in obj) {
        if (obj.hasOwnProperty(key)) {
          const newKey = renameMap[key] || key;
          objCopy[newKey] = this.deepCopyAndRename(obj[key], renameMap);
        }
      }
      return objCopy;
    },
  },
};
</script>

<template>
  <section>
    <div class="container py-4">
      <div class="row">
        <h2 class="text-center">
          {{ needUpdate ? 'edite diary' : 'write diary' }}
        </h2>

        <div class="card">
          <div class="card-body">
            <!-- date picker 사용 -->
            <div class="input-group input-group-static my-3">
              <label>Date</label>
              <input
                v-model="diaryModel.writtenDate"
                type="date"
                class="form-control"
              />
            </div>

            <div class="mb-4">
              <div class="input-group input-group-static">
                <label>title</label>
                <input
                  v-model="diaryModel.diaryTitle"
                  type="text"
                  class="form-control"
                  placeholder="제목"
                />
              </div>
            </div>
            <div class="mb-4">
              <div class="dropdown">
                <a
                  href="#"
                  class="btn dropdown-toggle"
                  style="background-color: #5d6443; color: #ffffff"
                  data-bs-toggle="dropdown"
                  id="navbarDropdownMenuLink2"
                >
                  team list
                </a>
                <ul
                  class="dropdown-menu"
                  aria-labelledby="navbarDropdownMenuLink2"
                >
                  <li>
                    <a
                      @click="
                        addToTeamListToShare(team.team_id, team.team_name)
                      "
                      class="dropdown-item"
                      v-for="(team, idx) in filteredTeamData"
                      href="javascript:void(0);"
                    >
                      {{ team.team_name }}
                    </a>
                  </li>
                </ul>
              </div>

              <div id="recipient_input_list">
                <span
                  v-for="(team, idx) in needUpdate
                    ? editedTeamList
                    : teamListToShare"
                  :key="idx"
                  class="me-1 badge align-items-center p-1 pe-2 text-success-emphasis bg-success-subtle border border-success-subtle rounded-pill d-inline-flex align-items-center"
                >
                  <span style="margin-left: 10px">{{ team.team_name }}</span>
                  <span class="vr mx-2"></span>
                  <a
                    href="javascript:void(0);"
                    @click="removeFromTeamListToShare(team.id)"
                  >
                    <span class="material-icons opacity-6 me-2 text-md"
                      >cancel</span
                    >
                  </a>
                </span>
              </div>
            </div>

            <!-- details -->
            <div class="input-group mb-4 input-group-static">
              <label>Details</label>
              <textarea
                v-model="diaryModel.details"
                name="message"
                class="form-control"
                id="message"
                rows="4"
              ></textarea>
            </div>
          </div>

          <div class="card-footer">
            <!-- button & modal -->
            <div class="d-flex justify-content-end">
              <button
                @click="writeOrUpdateDiary"
                class="btn me-2"
                style="background-color: #638589; color: #ffffff"
              >
                {{ needUpdate ? 'edite' : 'write diary' }}
              </button>
              <button
                class="btn"
                data-bs-toggle="modal"
                data-bs-target="#cancelModal"
                style="background-color: #d1c5ab"
              >
                cancel
              </button>
              <cancelModal :message="cancelMessage" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style>
@import '../../assets/styles.css?v=1.0';

.card {
  width: 700px;
  margin: 0 auto;
}
.card-body {
  width: 700px;
  /* 원하는 너비로 설정 */
  margin: 0 auto;
  /* 가운데 정렬 */
}
</style>
