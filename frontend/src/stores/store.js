import { createStore } from "vuex";
const BASE_URL = import.meta.env.VITE_API_BASE_URL;

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
    teamList: [],
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
    setTeamList(state, teamList) {
      state.teamList = teamList;
    },
    setDiaryData(state, diaryData) {
      state.diaryData = diaryData;
    },
  },

  actions: {
    async fetchStoreData({ commit, state }) {
      const menuList = [
        {
          type: "teams",
          url: `${BASE_URL}/members/userTeamList/${state.userId}`,
        },
        {
          type: "alarms",
          url: `${BASE_URL}/members/invited/${state.userId}`,
        },
        {
          type: "diaries",
          url: `${BASE_URL}/diaries/all/${state.userId}`,
        },
      ];

      const requests = menuList.map(async (dataReq) => {
        const res = await fetch(dataReq.url);
        return res.json();
      });

      const dataList = await Promise.all(requests);

      commit("setTeamList", dataList[0].data);
      commit("setinviteData", dataList[1].data);
      commit("setDiaryData", dataList[2].data);

      // dataList.forEach((data, idx) => {
      //   commit(`set${menuList[idx].type.charAt(0).toUpperCase() + menuList[idx].type.slice(1)}Data`, data.data);
      // });
    },

    // updateTeamList({ commit }, teamList) {
    //   commit('setTeamList', teamList);
    // },
    // getters: {
    //   teamList: state => state.teamList
    // }
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
