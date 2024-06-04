<script>
export default {
  components: {
  },
  mounted() {
    this.fetchData();

  },
  data() {
    return {
      userId: 0,
      alarmN: 0,

      dataList: null,
      dataTypeMap: new Map(),
      diariesData: null,
      teamsData: null,
      membersData: null,
      usersData: null,
      alarmData: null,

      str: '',

      isCalendar: true,
      dropdownText: 'calendar',
      // 사용자가 속한 team의 이름을 받아와서 배열에 저장.
      // team name 클릭하면 team page로 이동. (team 정보를 가지고서.)
    }
  },
  methods: {
    async fetchData() {
      // console.log(this.$store.state.userId);
      this.userId = this.$store.state.userId;

      const menuList = await Promise.all([
        { type: 'diaries', url: `http://localhost:8080/diaries` },
        { type: 'teams', url: `http://localhost:8080/members/userTeamList/${this.userId}` },
        { type: 'members', url: `http://localhost:8080/members` },
        { type: 'users', url: `http://localhost:8080/users` },
        { type: 'alarms', url: `http://localhost:8080/members/invited/${this.userId}` }

      ])

      const requests = menuList.map(async (dataReq) => {
        const res = await fetch(dataReq.url)
        return res.json()
      })


      this.dataList = await Promise.all(requests)
      this.dataTypeMap = new Map(this.dataList.map((data, idx) => [menuList[idx].type, data.data]))
      // console.log(this.dataTypeMap, 'dm')
      this.diariesData = this.dataTypeMap.get('diaries')
      this.teamsData = this.dataTypeMap.get('teams')
      this.membersData = this.dataTypeMap.get('members')
      this.usersData = this.dataTypeMap.get('users')
      this.alarmData = this.dataTypeMap.get('alarms')

      this.alarmN = this.alarmData ? this.alarmData.length : 0;

      // console.log(this.diariesData, this.teamsData, this.membersData, this.usersData)
    },
    setView(text) {
      this.dropdownText = text
      this.isCalendar = text === 'calendar'
    },

    async acceptInvite(alarm) {
      // console.log(alarm)
      try {
        const response = await fetch(`http://localhost:8080/members/${alarm.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            user_id: this.userId,
            status: 0,
            team_id: alarm.team_id,
            inviter_id: alarm.inviter_id

          })
        });
        if (response.ok) {
          this.alarmData = this.alarmData.filter(alarm => alarm.id !== alarm.id);
          this.alarmN = this.alarmData.length;
        } else {
          console.error('Error accepting invite');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

    async refuseInvite(alarmId) {
      try {
        const response = await fetch(`http://localhost:8080/members/${alarmId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (response.ok) {
          this.alarmData = this.alarmData.filter(alarm => alarm.id !== alarmId);
          this.alarmN = this.alarmData.length;
        } else {
          console.error('Error accepting invite');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    },

    // log out
    logOut() {
      this.$store.commit('logOut');
      console.log(this.$store.state.userId);
      this.$router.push({ path: 'logIn' });
    }

  }


}
</script>

<template>
  <div>
    <p v-if="!dataList">로딩...</p>
    <div v-else>
      <div class="row">
        <div class="col-2">
          <button type="button" class="btn bg-gradient-info">create group</button>

          <div class="list-group" v-if="teamsData">
            <a href="/team" class="list-group-item list-group-item-action" v-for="(team, idx) in teamsData" :key="idx">
              {{ team.team_name }}
            </a>
          </div>
        </div>
        <div class="col-10">
          <div class="row">
            <div class="d-flex align-items-center">
              <div class="dropdown mx-1">

                <button type="button" class="btn btn-info position-relative dropdown-toggle" data-bs-toggle="dropdown"
                  data-bs-auto-close="false" aria-expanded="false">
                  <span>invite alarms </span>
                  <span class="badge badge-sm badge-circle badge-danger border border-white border-2">
                    {{ alarmN }}
                  </span>
                </button>

                <ul class="dropdown-menu">

                  <ul class="list-group">
                    <li class="list-group-item" v-for="(alarm, idx) in alarmData" :key="idx">
                      <p>invited from ' {{ alarm.first_name }} '</p>
                      <p>group name: {{ alarm.team_name }}</p>
                      <row>
                        <button @click="acceptInvite(alarm)" type="button" class="btn bg-gradient-info">accept</button>
                        <button @click="refuseInvite(alarm.id)" type="button"
                          class="btn bg-gradient-danger">refuse</button>
                      </row>
                    </li>
                  </ul>


                  <!-- <li class="dropdown-item" v-for="(alarm, idx) in alarmData" :key="idx">
                    <p>invited from ' {{ alarm.inviter_id }} '</p>
                    <p>group name: {{ alarm.team_name }}</p>
                    <row>
                      <button>refuse</button>
                      <button>accept</button>
                    </row>
                  </li> -->

                  <li>
                    <hr class="dropdown-divider">
                  </li>
                  <li><a class="dropdown-item" href="#">Separated link</a></li>
                </ul>

              </div>
              <div class="btn-group mx-1">
                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                  data-bs-auto-close="true" aria-expanded="false">
                  {{ dropdownText }}
                </button>
                <ul class="dropdown-menu">
                  <button class="dropdown-item" @click="setView('calendar')">calendar</button>
                  <button class="dropdown-item" @click="setView('list')">list</button>
                </ul>
              </div>
              <a href="/writeDiary" class="btn btn-info mx-1">write diary</a>

              <button type="button" class="btn btn-twitter">
                <span @click="logOut" class="btn-inner--text">log-out</span>
              </button>

            </div>
          </div>
          <div class="row">
            <div class="col-6">

            </div>
            <div class="col-6">
            </div>
          </div>

          <h1 v-if="isCalendar">calendar</h1>
          <div v-else>
            <div class="card-group mb-3" v-for="(item, idx) in diariesData" :key="idx">
              <div class="card">
                <div class="card-header">
                  <div class="d-flex align-items-center">
                    <h6>
                      {{ item.written_date }}-{{ item.diary_title }}
                    </h6>
                    <button class="btn btn-link text-info ">
                      <i class="material-icons text-lg">edit</i>
                    </button>
                  </div>
                </div>
                <div class="card-body text-center">
                  <p class="mb-0">
                    {{ item.details }}
                  </p>
                </div>
                <hr class="dark horizontal my-0">
                <div class="card-footer d-flex">
                  {{ item.writer_id }}
                </div>
              </div>
            </div>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">written_date</th>
                  <th scope="col">writer_id</th>
                  <th scope="col">diary_title</th>
                  <th scope="col">details</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(item, idx) in diariesData" :key="idx">
                  <td>{{ item.written_date }}</td>
                  <td>{{ item.writer_id }}</td>
                  <td>{{ item.diary_title }}</td>
                  <td>{{ item.details }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
