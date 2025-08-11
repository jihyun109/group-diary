<template>
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
          <h5 class="modal-title" id="modal-title-notification">멤버 추가</h5>
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
                      @click="removeFromInviteGroup(user.userId)"
                      style="cursor: pointer;"
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
                    class="list-group-item list-group-item-action"
                    style="cursor: pointer;"
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
</template>

<script>
import { mapState } from 'vuex';
import { fetchUserSearch } from '@/api/user.js';
import { inviteUser } from '@/api/member.js';

export default {
  name: 'InviteUserModal',

  props: {
    teamId: {
      type: [String, Number],
      required: true,
    },
  },

  emits: ['users-invited'],

  data() {
    return {
      searchWord: '',
      userSearchData: [],
      usersToInvite: [],
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
    async searchUser() {
      this.userSearchData = await fetchUserSearch(this.searchWord);
    },

    updateClickedUserInfo(userId, lastName, firstName) {
      this.usersToInvite.push({ userId, lastName, firstName });
    },

    removeFromInviteGroup(userId) {
      this.usersToInvite = this.usersToInvite.filter(
        (user) => user.userId != userId
      );
    },

    resetUsersToInvite() {
      this.usersToInvite = [];
      this.searchWord = '';
      this.userSearchData = [];
    },

    async inviteToTeam() {
      for (let i = 0; i < this.usersToInvite.length; i++) {
        let userId = this.usersToInvite[i].userId;
        await this.requestInviteUser(userId, this.teamId);
      }
      alert('팀에 초대되었습니다.');

      // 모달 닫기
      const modalElement = document.getElementById('inviteUser-form');
      const modal = bootstrap.Modal.getInstance(modalElement);
      if (modal) {
        modal.hide();
      }

      this.resetUsersToInvite();

      // 부모 컴포넌트에 이벤트 발생
      this.$emit('users-invited');
    },

    async requestInviteUser(userId, teamId) {
      await inviteUser(userId, teamId, this.userId);
    },
  },
};
</script>
