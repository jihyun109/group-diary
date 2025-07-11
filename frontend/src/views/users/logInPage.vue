<script>
const BASE_URL = import.meta.env.VITE_API_BASE_URL;
export default {
  mounted() {
    this.fetchData();
  },
  data() {
    return {
      dataList: [],
      email: '',
      password: ''
    }
  },
  methods: {
    async logIn() {
      try {
        const response = await fetch(import.meta.env.VITE_API_BASE_URL + '/users/logIn', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ email: this.email, password: this.password })
        });

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log(data);

        if (data && data.result === 'success'&& data.data.id) {
          alert('로그인이 완료되었습니다.');
          console.log("login data: " + data);

          this.$store.commit('setUserId', data.data.id); 
          this.$store.commit('setFirstName', data.data.firstName); 
          this.$store.commit('setLastName', data.data.lastName); 
          this.$store.commit('setEmail', this.email); 
          this.$store.commit('setPassword', this.password); 
          this.$store.commit('setColor', data.data.color); 

          this.$router.push({ path: 'main'});
        } else {
          console.log(data);
          console.log(data.data.id);
          alert('이메일 또는 비밀번호가 올바르지 않습니다.');
        }
      } catch (error) {
        console.error('Fetch 작업에 문제가 발생했습니다:', error);
        alert('로그인 실패');
      }
    },

    async fetchData() {
      const urls = [
        `${BASE_URL}/users`
      ];

      const requests = urls.map(async url => {
        const res = await fetch(url);
        return res.json();
      });

      this.dataList = await Promise.all(requests);
      console.log(this.dataList);
    }
  }
}
</script>

<template>
  <div class="page-header align-items-start min-vh-100" style="background-image: url('../src/assets/img/LogInBG.jpg');"
  loading="lazy">
    <span class="opacity-6"></span>
    <div class="container my-auto">
      <div class="row">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="shadow-info border-radius-lg py-3 pe-1" style="background-color: #4f684e;">
                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0" >Log in</h4>
              </div>
            </div>

            <div class="card-body">
              <form role="form" class="text-start">

                <div class="input-group input-group-outline my-3">
                  <input placeholder="email" type="email" class="form-control" v-model="email">
                </div>

                <div class="input-group input-group-outline mb-3">
                  <input placeholder="password" type="password" class="form-control" v-model="password">
                </div>

                <div class="text-center">
                  <button type="button" @click="logIn" class="btn w-100 my-4 mb-2" style="background-color: #728462; color: #ffffff">Log in</button>
                </div>

                <div class="mt-4 text-sm text-center">
                  <span>Don't have an account?</span>
                  <a href='/signUp' class="text-dark ms-1"><u>Sign Up</u></a>
                </div>

              </form>

            </div>
          </div>
        </div>
      </div>
    </div>
    <footer class="footer position-absolute bottom-2 py-2 w-100">
      <div class="container">
        <div class="row align-items-center justify-content-lg-between">
          <div class="col-12 col-md-6 my-auto">

          </div>
        </div>
      </div>
    </footer>
  </div>

</template>

<style>
body {
    background-color: #afbda4 !important;
  }

.card {
  margin-top: 0px;
}
</style>