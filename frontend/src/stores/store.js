import { createStore } from 'vuex';

const store = createStore({
  state: {
    userId: '',
    email: '',
    password: ''
  },
  mutations: {
    setUserId(state, userId) {
        state.userId = userId
    },
    setEmail(state, email) {
      state.email = email;
    },
    setPassword(state, password) {
      state.password = password;
    },
    logOut(state) {
        state.userId = '';
      state.email = '';
      state.password = '';
    }
  }
});

export default store;