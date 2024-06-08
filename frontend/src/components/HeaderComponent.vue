<script>
import { mapState } from 'vuex';

export default {
  mounted() {

  },
  methods: {
    async fetchData() {
      console.log(this.firstName);
      // console.log(this.userId);

      console.log('team: ', this.$route.query.team)
      // this.userId = this.$store.state.userId;
      const inviteDataResponse = await fetch(`http://localhost:8080/members/invited/${this.userId}`)
      console.log("at header", inviteDataResponse)
      this.inviteData = inviteDataResponse.json().data
      // this.alarmN = this.inviteData ? this.inviteData.length : 0;

      // console.log(this.diariesData, this.teamsData, this.membersData, this.usersData)
    },
    // log out
    logOut() {
      this.$store.commit('logOut');
      this.$router.push({ path: 'logIn' });
      console.log(this.userId);
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
  },
  data() {
    return {
      inviteData: [],
      menuList: [

        {
          name: "main",
          icon: "star",
          url: "/main"
        },
        {
          name: "writeDiary",
          icon: "star",
          url: "/writeDiary"
        },

      ]
    }
  },
  computed: {
    ...mapState(['userId', 'firstName', 'lastName']),
  }

}
</script>

<template>

  <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
    <div class="container">
      <a class="navbar-brand" href="/main" rel="tooltip" title="Designed and Coded by Creative Tim"
        data-placement="bottom">
        지현 다이어리
      </a>

      <div class="container navbar-collapse  pt-3 pb-2 py-lg-0 ms-lg-12 ps-lg-5">
        <ul class="navbar-nav navbar-nav-hover ms-auto" v-for="{ name, url, icon }, idx in menuList" :key="idx">
          <li class="nav-item dropdown dropdown-hover mx-2 ms-lg-5">
            <a class="nav-link ps-2 d-flex justify-content-between cursor-pointer align-items-center" :href=url
              aria-expanded="false">
              <i class="material-icons opacity-6 me-2 text-md">{{ icon }}</i>
              {{ name }}

            </a>

          </li>

        </ul>
        <div class="d-flex align-items-center">
          <!-- userInfo -->
          <a class="nav-link" href="/userInfo">
            <div>
              {{ this.lastName }}
              {{ this.firstName }}
            </div>
          </a>


          <!-- log out -->
          <button type="button" class="btn btn-twitter">
            <span @click="logOut" class="btn-inner--text">log-out</span>
          </button>

          <div class="dropdown mx-1">

            <!-- 초대 알림 -->
            <!-- 버튼 -->
            <button type="button" class="btn btn-info position-relative dropdown-toggle" data-bs-toggle="dropdown"
              data-bs-auto-close="false" aria-expanded="false">
              <i class="material-icons opacity-6 me-2 text-md">notifications</i>

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
        </div>

      </div>
    </div>
  </nav>




</template>
