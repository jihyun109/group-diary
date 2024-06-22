import { createStore } from "vuex";

const store = createStore({
  state: {
    userId: 0,
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    color: "",

    inviteData: [],
    diaryData: [],
    teamData: [],
  },
  mutations: {
    setUserId(state, userId) {
      state.userId = userId;
    },
    setFirstName(state, firstName) {
      state.firstName = firstName;
    },
    setLastName(state, lastName) {
      state.lastName = lastName;
    },
    setEmail(state, email) {
      state.email = email;
    },
    setPassword(state, password) {
      state.password = password;
    },
    setColor(state, color) {
      state.color = color;
    },
    logOut(state) {
      sessionStorage.clear();
      state.userId = 0; // userId 초기화
      state.firstName = ""; 
      state.lastName = ""; 
      state.email = ""; // email 초기화
      state.password = ""; // password 초기화
      state.inviteData = []; // inviteData 초기화
      state.diaryData = []; // diaryData 초기화
      state.teamData = []; // teamData 초기화
      state.color = ""; // teamData 초기화
    },
    setinviteData(state, inviteData) {
      state.inviteData = inviteData;
    },
    setTeamData(state, teamData) {
      state.teamData = teamData;
    },
    setDiaryData(state, diaryData) {
      state.diaryData = diaryData;
    },
  },

  actions: {
    async fetchData({ commit, state }) {
      const menuList = [
        {
          type: "teams",
          url: `http://localhost:8080/members/userTeamList/${state.userId}`,
        },
        {
          type: "alarms",
          url: `http://localhost:8080/members/invited/${state.userId}`,
        },
        {
          type: "diaries",
          url: `http://localhost:8080/diaries/all/${state.userId}`,
        },
      ];

      const requests = menuList.map(async (dataReq) => {
        const res = await fetch(dataReq.url);
        return res.json();
      });

      const dataList = await Promise.all(requests);

      commit("setTeamData", dataList[0].data);
      commit("setinviteData", dataList[1].data);
      commit("setDiaryData", dataList[2].data);

      // dataList.forEach((data, idx) => {
      //   commit(`set${menuList[idx].type.charAt(0).toUpperCase() + menuList[idx].type.slice(1)}Data`, data.data);
      // });
    },
  },

  plugins: [
    (store) => {
      store.subscribe((mutation, state) => {
        // 상태 변경시 로컬 스토리지에 저장
        sessionStorage.setItem("store", JSON.stringify(state));
      });
    },
  ],
});

// 저장된 상태가 있을 경우 로컬 스토리지에서 불러오기
const savedState = sessionStorage.getItem("store");
if (savedState) {
  store.replaceState(Object.assign(store.state, JSON.parse(savedState)));
}

export default store;
