<!-- tag 사용 -->

<script>
import { mapState } from "vuex";
import cancelModal from "@/components/cancelModal.vue";
import '../../assets/styles.css?v=1.0';

export default {
  beforeMount() {
    console.log('beforeMount: needUpdate:', this.needUpdate);
  },
  mounted() {
    console.log('needUpdate:',this.needUpdate);
    this.updateNeedUpdate();
    if (this.needUpdate) {
      this.fetchDiaryDetail(this.$route.query.diaryId);
      this.requestSharedTeams();
    }

    this.fetchTeamData();
    // console.log('sharedTeamId: ', this.sharedTeamId);
  },
  components: {
    cancelModal,
  },
  props: {
    diaryData: Object,
  },
  data() {
    return {
      dataList: [],

      diaryModel: {
        id: "",
        written_date: "",
        diary_title: "",
        details: "",
      },

      diaryId: this.$route.query.diaryId,

      cancelMessage: "일기 작성",

      teamListToShare: [], // 일기를 공유할 팀들의 id 저장 리스트
      teamData: [],
      sharedTeamId: [], // 일기가 공유된 team id
      editedTeamList: [], // 수정된 팀 리스트
      addedTeamList: [],
      deletedTeamList: [],
      needUpdate: false
    };
  },

  computed: {
    ...mapState(["userId", "firstName", "lastName"]),
    // needUpdate() {
    //   const update = !!this.$route.query.diaryId;
    //   console.log("needUpdate: ", update);
    //   return update
    //   // if (this.$route.query.diaryId) {
    //   //   return true
    //   // }
    // },

    filteredTeamData() {
      if (!this.needUpdate) {
        const teamIdToShare = new Set(
          this.teamListToShare.map((team) => team.id)
        );
        return this.teamData.filter((team) => !teamIdToShare.has(team.team_id));
      } else {
        console.log("teamData: ", this.teamData);
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
      console.log("needUpdate:", this.needUpdate);
    },
    async writeOrUpdateDiary() {
      await this.requestPostOrUpdateDiary();

      console.log(this.needUpdate);
      // 일기 작성 페이지이면
      if (!this.needUpdate) {
        const diaryId = await this.requestThisDiaryId();
        console.log("diaryId: ", diaryId);

        // 선택한 팀들에 일기 공유
        for (let i = 0; i < this.teamListToShare.length; i++) {
          console.log("teamListToShare: ", this.teamListToShare);
          const teamId = this.teamListToShare[i].id;
          console.log("teamId: ", teamId);
          await this.requestShareDiary(diaryId, teamId);
        }

        // this.$router.push({ name: "main" })
        this.$router.go(-1);
        alert("일기 작성 완료");
      } else {
        // 일기 수정 페이지
        await this.requestEditedTeamList();
        this.$router.go(-1);
        alert("일기 수정 완료");
      }
    },

    async requestPostOrUpdateDiary() {
      // 일기 post or put
      var requestUrl = "http://localhost:8080/diaries";
      var method = "";
      if (this.needUpdate) {
        requestUrl += `/edit/${this.diaryModel.id}`;
        method = "PUT";
      } else {
        method = "POST";
      }

      console.log("diaryModel: ", this.diaryModel);

      this.diaryModel.writer_id = this.userId;

      const response = await fetch(requestUrl, {
        method: method,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.diaryModel),
      });

      if (response.ok) {
        // 요청이 성공하면 성공 메시지 표시
        console.log(`${this.needUpdate ? "수정" : "작성"} 성공 `);
        // if (this.needUpdate) {
        //   this.$router.push({ name: "diaryDetail", query: { diary: this.diaryModel.id } })
        // } else {
        //   this.$router.push({ name: "main" })
        //   // this.$router.go(-1);
        // }
      } else {
        // 요청이 실패하면 오류 메시지 표시
        const errorData = await response.json();
        console.log(`오류가 발생했습니다: ${errorData.message}`);
      }
    },

    async fetchDiaryDetail(id) {
      this.diary = null;
      const res = await fetch(`http://localhost:8080/diaries/details/${id}`);
      const resBody = await res.json();
      this.diaryModel = resBody.data;
      console.log("diaryModel: ", this.diaryModel);
      this.editedTeamList = this.deepCopyAndRename(
        this.diaryModel.sharedTeamList
      );
      console.log("editedTeamList: ", this.editedTeamList);
      // JSON.parse(JSON.stringify(this.diaryModel.sharedTeamList));
    },

    formattedDate(diary) {
      if (diary.written_date) {
        const date = diary.written_date;
        return `Written Date: ${date.slice(0, 2)}-${date.slice(
          2,
          4
        )}-${date.slice(4)}`;
      }
      return "";
    },

    // 사용자가 멤버인 팀들의 데이터(team id, team name) fetch
    async fetchTeamData() {
      try {
        const response = await fetch(
          `http://localhost:8080/members/userTeamList/${this.userId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );
        if (response.ok) {
          const resjson = await response.json();
          this.teamData = resjson.data;
          console.log("fetchTeamData success.", this.teamData);
        } else {
          console.log("team data를 불러오지 못했습니다.");
        }
      } catch (error) {
        console.error("fail fetchTeamData. Error:", error);
      }
    },

    // 공유할 팀 리스트에 추가
    addToTeamListToShare(teamId, teamName) {
      if (!this.needUpdate) {
        this.teamListToShare.push({ id: teamId, team_name: teamName });
        console.log("teamListToShare: ", this.teamListToShare);
        console.log("filteredTeamData:", this.filteredTeamData);
      } else {
        console.log("editedTeamList: ", this.editedTeamList);
        this.editedTeamList.push({ id: teamId, team_name: teamName });
      }
    },

    removeFromTeamListToShare(teamId) {
      if (!this.needUpdate) {
        this.teamListToShare = this.teamListToShare.filter(
          (team) => team.id != teamId
        );
        console.log("teamListToShare: ", this.teamListToShare);
        console.log("filteredTeamData:", this.filteredTeamData);
      } else {
        {
          this.editedTeamList = this.editedTeamList.filter(
            (team) => team.id != teamId
          );
          console.log("editedTeamList: ", this.editedTeamList);
        }
      }
    },
    // 작성한 일기의 아이디 요청
    async requestThisDiaryId() {
      console.log("일기 아이디 요청");

      try {
        const response = await fetch(
          `http://localhost:8080/diaries/findDiaryId?diaryTitle=${encodeURIComponent(
            this.diaryModel.diary_title
          )}&writtenDate=${this.diaryModel.written_date}&writerId=${this.userId
          }`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.ok) {
          const resjson = await response.json();
          console.log("find diaryId. resjson: ", resjson);

          const diaryId = resjson.data[0].id;
          console.log("find diaryId. diaryId: ", diaryId);
          return diaryId;
        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          console.log(`diary not founded: ${errorData.message}`);
        }
      } catch (error) {
        // 네트워크 오류 처리
        console.log(
          `diary not founded. 네트워크 오류가 발생했습니다: ${error.message}`
        );
      }
    },

    async requestShareDiary(diaryId, teamId) {
      // 입력 데이터를 객체로 수집
      const shareDiaryData = {
        diary_id: diaryId,
        team_id: teamId,
      };

      console.log("diaryId:", diaryId, "teamId:", teamId);

      try {
        // 서버로 POST 요청 보내기
        const response = await fetch("http://localhost:8080/teamDiaries/", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(shareDiaryData),
        });

        if (response.ok) {
          // 요청이 성공하면 성공 메시지 표시
          console.log("일기 공유 성공: ", shareDiaryData);
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

    async requestSharedTeams() {
      try {
        const response = await fetch(
          `http://localhost:8080/teamDiaries/sharedTeams/${this.diaryModel.id}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.ok) {
          const resjson = await response.json();
          console.log("shared teams. resjson: ", resjson);

          const diaryId = resjson.data[0].id;
          console.log("find diaryId. diaryId: ", diaryId);
          return diaryId;
        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          console.log(`diary not founded: ${errorData.message}`);
        }
      } catch (error) {
        // 네트워크 오류 처리
        console.log(
          `diary not founded. 네트워크 오류가 발생했습니다: ${error.message}`
        );
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
      console.log(this.diaryModel.sharedTeamList);
      console.log(this.editedTeamList);

      this.addedTeamList = findAddedTeam(
        this.diaryModel.sharedTeamList,
        this.editedTeamList
      );
      this.deletedTeamList = findDeletedTeamList(
        this.diaryModel.sharedTeamList,
        this.editedTeamList
      );
      console.log("1.addedTeamList: ", this.addedTeamList);
      console.log("1.deletedTeamList: ", this.deletedTeamList);

      // 추가된 팀 공유 요청 보내기
      for (let i = 0; i < this.addedTeamList.length; i++) {
        await this.requestShareDiary(this.diaryId, this.addedTeamList[i].id);
        console.log(1);
      }

      // 삭제된 팀 delete 요청 보내기
      for (let i = 0; i < this.deletedTeamList.length; i++) {
        await this.requestCancelShareDiary(this.deletedTeamList[i].id);
      }
    },

    async requestCancelShareDiary(teamId) {
      try {
        const response = await fetch(
          `http://localhost:8080/teamDiaries?diaryId=${this.diaryId}&teamId=${teamId}`,
          {
            method: "DELETE",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (response.ok) {
          const resjson = await response.json();
          console.log(
            "공유 취소 성공. diaryId: ",
            this.diaryId,
            ", teamId:",
            teamId
          );
        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          console.log(`공유 취소 실패: ${errorData.message}`);
        }
      } catch (error) {
        // 네트워크 오류 처리
        console.log(
          `공유 취소 실패. 네트워크 오류가 발생했습니다: ${error.message}`
        );
      }
    },

    deepCopyAndRename(obj) {
      const renameMap = {
        teamId: "id",
        teamName: "team_name",
      };
      if (obj === null || typeof obj !== "object") {
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

    async fetchData() {
      const urls = [
        `http://localhost:8080/diaries`,
        `http://localhost:8080/teams`,
        `http://localhost:8080/members`,
      ];

      const requests = urls.map(async (url) => {
        const res = await fetch(url);
        return res.json();
      });

      this.dataList = await Promise.all(requests);
      console.log(this.dataList);
    },
  },
};
</script>

<template>
  <section>
    <div class="container py-4">
      <div class="row">
        <h2 class="text-center">
          {{ needUpdate ? "edite diary" : "write diary" }}
        </h2>

        <div class="card">
          <div class="card-body">
            <!-- date picker 사용 -->
            <div class="input-group input-group-static my-3">
              <label>Date</label>
              <input v-model="diaryModel.written_date" type="date" class="form-control" />
            </div>

            <div class="mb-4">
              <div class="input-group input-group-static">
                <label>title</label>
                <input v-model="diaryModel.diary_title" type="text" class="form-control" placeholder="제목" />
              </div>
            </div>
            <div class="mb-4">
              <div class="dropdown">
                <a href="#" class="btn dropdown-toggle" style="background-color: #5d6443;color: #ffffff;"  data-bs-toggle="dropdown"
                  id="navbarDropdownMenuLink2">
                  team list
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
                  <li>
                    <a @click="
                      addToTeamListToShare(team.team_id, team.team_name)
                      " class="dropdown-item" v-for="(team, idx) in filteredTeamData" href="javacsript:void(0);">
                      {{ team.team_name }}
                    </a>
                  </li>
                </ul>
              </div>

              <div id="recipient_input_list">
                <span v-for="(team, idx) in needUpdate
                  ? editedTeamList
                  : teamListToShare" :key="idx"
                  class="me-1 badge align-items-center p-1 pe-2 text-success-emphasis bg-success-subtle border border-success-subtle rounded-pill d-inline-flex align-items-center">
                  <span style="margin-left: 10px">{{ team.team_name }}</span>
                  <span class="vr mx-2"></span>
                  <a href="javacsript:void(0);" @click="removeFromTeamListToShare(team.id)">
                    <span class="material-icons opacity-6 me-2 text-md">cancel</span>
                  </a>
                </span>
              </div>
            </div>

            <!-- details -->
            <div class="input-group mb-4 input-group-static">
              <label>Details</label>
              <textarea v-model="diaryModel.details" name="message" class="form-control" id="message"
                rows="4"></textarea>
            </div>
          </div>

          <div class="card-footer">
            <!-- button & modal -->
            <div class="d-flex justify-content-end">
              <button @click="writeOrUpdateDiary" class="btn  me-2" style="background-color: #638589; color: #ffffff;">
                {{ needUpdate ? "edite" : "write diary" }}
              </button>
              <button class="btn " data-bs-toggle="modal" data-bs-target="#cancelModal" style="background-color: #d1c5ab;">
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
