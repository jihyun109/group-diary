<template>
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
          <h5 class="modal-title" id="modal-title-notification">
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
                    type="text"
                    class="form-control"
                    :class="{ 'is-invalid': errors.groupNameToCreate }"
                    placeholder="Group Name"
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
                </form>
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
                    href="javascript:void(0);"
                    class="list-group-item list-group-item-action"
                  >
                    {{ user.lastName }} {{ user.firstName }}
                  </a>
                </div>
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
</template>

<script>
import { mapState } from 'vuex';
import { fetchUserSearch } from '@/api/user.js';
import { createTeam } from '@/api/team.js';
import { inviteUser } from '@/api/member.js';


const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export default {
  name: 'CreateTeamModal',
  
  props: {
    // 부모로부터 받을 props
  },

  emits: ['team-created'],

  data() {
    return {
      searchWord: '',
      groupNameToCreate: '',
      userSearchData: [],
      usersToInvite: [],
      errors: {
        groupNameToCreate: false,
      },
    };
  },

  computed: {
    ...mapState(['userId']),

    filteredUserSearchData() {
      const invitedUserIds = new Set(
        this.usersToInvite.map((user) => user.userId)
      );
      return this.userSearchData.filter(
        (user) => user.id !== this.userId && !invitedUserIds.has(user.id)
      );
    },
  },

  methods: {
    async updateTeamList() {
      // 부모 컴포넌트의 updateTeamList 메서드 호출
      this.$emit('team-created');
    },

    async searchUser() {
      this.userSearchData = await fetchUserSearch(this.searchWord);
    },

    addToInviteGroup(userId, lastName, firstName) {
      this.usersToInvite.push({ userId, lastName, firstName });
    },

    removeFromInviteGroup(userId) {
      this.usersToInvite = this.usersToInvite.filter(
        (user) => user.userId != userId
      );
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
      this.resetUsersToInvite();

      // 모달 닫기
      const modalElement = document.getElementById('createGroup-form');
      const modal = bootstrap.Modal.getInstance(modalElement);
      if (modal) {
        modal.hide();
      }

      // 부모 컴포넌트에 이벤트 발생
      this.$emit('team-created');
    },

    async fetchData() {
      // 부모 컴포넌트의 fetchData 메서드 호출
      this.$emit('team-created');
    },

    async requestCreateTeam() {
      this.errors.groupNameToCreate = !this.groupNameToCreate;

      if (this.errors.groupNameToCreate) {
        alert('그룹 이름을 입력해 주세요');
        return;
      }
      console.log("groupNameToCreate: ", this.groupNameToCreate);

      await createTeam(this.userId, this.groupNameToCreate);
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
      await inviteUser(userId, teamId, this.userId);
    //   const inviteData = {
    //     userId: userId,
    //     teamId: teamId,
    //     status: userId === this.userId ? 0 : 1,
    //     inviterId: this.userId,
    //   };

    //   try {
    //     const response = await fetch(`${BASE_URL}/members`, {
    //       method: 'POST',
    //       headers: {
    //         'Content-Type': 'application/json',
    //       },
    //       body: JSON.stringify(inviteData),
    //     });

    //     if (response.ok) {
    //       console.log('초대 성공: ', inviteData);
    //     } else {
    //       const errorData = await response.json();
    //       console.log(`오류가 발생했습니다: ${JSON.stringify(errorData)}`);
    //     }
    //   } catch (error) {
    //     alert(`네트워크 오류가 발생했습니다: ${error.message}`);
    //   }
    },

    async requestTeamId() {
      try {
        const response = await fetch(
          `${BASE_URL}/teams/findId?teamName=${encodeURIComponent(this.groupNameToCreate)}&creatorId=${this.userId}`,
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
    },
  },
};
</script> 