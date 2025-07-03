<template>
  <nav v-if="showNavbarContent" class="navbar navbar-expand-lg position-absolute top-0 z-index-3 w-100 shadow-none my-3 navbar-transparent">
    <div class="container d-flex align-items-center justify-content-between">
      <a class="navbar-brand d-flex align-items-center" href="/main" rel="tooltip"
        title="Designed and Coded by Creative Tim" data-placement="bottom">
        <img src="../assets/img/일기 로고.jpg" style="height: 30px; margin-right: 10px;">
        Friend Log
      </a>

      <div class="d-flex align-items-center" v-if="showNavbarContent">
        <div class="d-flex align-items-center">
          <div class="dropdown mx-1 position-relative">
            <!-- 초대 알림 -->
            <!-- 아이콘 -->
            <a class="nav-link position-relative cursor-pointer me-2" data-bs-toggle="dropdown" data-bs-auto-close="false"
              aria-expanded="false">
              <img src="../assets/img/alarm.png" style="height: 30px; margin-right: -15px;">

              <!-- badge -->
              <span v-if="alarmN > 0" class="badge badge-circle border border-white border-2"
                :style="{ backgroundColor: badgeColor, fontSize: '0.5rem', top: '10px', right: '5px', width: '23px', height: '23px', borderRadius: '50%', display: 'flex', alignItems: 'center', justifyContent: 'center' }">
                {{ alarmN }}
              </span>
            </a>
            <!-- dropdown -->
            <ul class="dropdown-menu">
              <ul class="list-group notification-list">
                <li class="list-group-item" v-for="(invite, idx) in inviteData" :key="idx">
                  <strong>{{ invite.first_name }}</strong> 님이 <strong>{{ invite.team_name }}</strong> 그룹에 초대했습니다.
                  <div class="d-flex justify-content-between mt-2">
                    <button @click="requestAcceptInvite(invite)" type="button"
                      class="btn me-2"  style="background-color: #638589; color: #ffffff;">accept</button>
                    <button @click="requestRefuseInvite(invite.id)" type="button"
                      class="btn" style="background-color: #d1c5ab;">refuse</button>
                  </div>
                </li>
              </ul>
            </ul>
          </div>
          <!-- userInfo -->
          <a class="nav-link d-flex align-items-center me-2" href="/userInfo">
            <UserProfile class="rounded-circle me-2" :fontSize="15" :width="45" :height="45" :color="this.color"
              :firstName="this.firstName"></UserProfile>
            <div style="font-size: medium; color: #423031;">
              <strong>{{ this.lastName }} {{ this.firstName }}</strong>
            </div>
          </a>
          <!-- log out -->
          <i type="button" @click="logOut" class="material-icons logout-icon">logout</i>
        </div>
      </div>
    </div>
  </nav>
</template>


<script>
import { mapActions } from 'vuex';
import { mapState } from 'vuex';
import UserProfile from '@/components/UserProfile.vue';
const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export default {
  mounted() {
    this.$store.dispatch('fetchStoreData');
    this.fetchData();
  },
  watch: {
    // 페이지가 업데이트될 때마다 fetchData를 호출하여 알림 데이터를 새로 불러옵니다.
    '$route': 'fetchData'
  },
  components: {
    UserProfile
  },
  methods: {
    ...mapActions(['fetchStoreData']),
    async fetchData() {
      console.log(this.firstName);
      const inviteDataResponse = await fetch(`${BASE_URL}/members/invited/${this.userId}`);
      console.log("at header", inviteDataResponse);

      const inviteDataJson = await inviteDataResponse.json();
      this.inviteData = inviteDataJson.data;
      this.alarmN = this.inviteData.length;


      // console.log("inviteData: ", this.inviteData);
    },

    logOut() {
      this.$store.commit('logOut');
      this.$router.push({ path: 'logIn' });
      console.log(this.userId);
    },

    async requestAcceptInvite(invite) {
      try {
        const response = await fetch(`${BASE_URL}/members/${invite.id}`, {
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
          this.inviteData = this.inviteData.filter(i => i.id !== invite.id);
          this.alarmN = this.inviteData.length;
          await this.fetchStoreData();
        } else {
          console.error('Error accepting invite');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

    async requestRefuseInvite(inviteId) {
      try {
        const response = await fetch(`${BASE_URL}/members/${inviteId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (response.ok) {
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
      alarmN: 0,
      badgeColor: 'red',
      menuList: [
        {
          name: "Diary",
          icon: "star",
          url: "/writeDiary"
        },
      ]
    }
  },
  computed: {
    ...mapState(['userId', 'firstName', 'lastName', 'color']),
    showNavbarContent() {
    const hidePaths = ['/logIn', '/signUp'];
    return !hidePaths.includes(this.$route.path);
  }
  }
}
</script>

<style scoped>
.material-icons.alarm {
  font-size: 36px;
}

.badge {
  position: absolute;
  top: -5px;
  right: -5px;
  font-size: 0.75rem;
  transform: translate(50%, -50%);
}

.material-icons.logout-icon {
  font-size: 24px; /* 아이콘 크기를 작게 조정 */
  margin-left: -5px; /* 이름과 아이콘 간격 조정 */
  margin-right: 20px;
}

.notification-list {
  max-height: 500px;
  overflow-y: auto;
}

.team-list-scroll {
  max-height: 200px; /* 원하는 높이 설정 */
  overflow-y: auto; /* 세로 스크롤바 추가 */
}
</style>
