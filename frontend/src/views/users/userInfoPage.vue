<script>
export default {
  mounted() {
    this.fetchUserInfoData()
  },
  data() {
    return {
      userId: 0,
      userDataList: [],
      userInfoData: null,

      firstName: '',
      lastName: '',
      emailAddress: '',
      password: '',
      initial: '',
      color: '',
    }
  },
  methods: {
    async fetchUserInfoData() {
      this.userId = this.$store.state.userId;
      const urls = [
        `http://localhost:8080/users`
      ];

      const requests = urls.map(async url => {
        const res = await fetch(url);
        return res.json();
      });

      const responses = await Promise.all(requests);
      this.userDataList = responses.flatMap(response => response.data);

      this.getUserDataById()
    },

    getUserDataById() {
      this.userData = this.userDataList.find(user => user.id === this.userId);
      console.log(this.userData);

      if (this.userData) {
        this.firstName = this.userData.first_name;
        this.lastName = this.userData.last_name;
        this.emailAddress = this.userData.email;
        this.password = this.userData.password;
        this.initial = this.userData.initial;
        this.color = this.userData.color;

      }
    },

    moveToEditPage() {
      this.$router.push({ path: 'userInfoEdit' });
    }
  }
}
</script>

<template>
  <div class="page-header align-items-start min-vh-100" style="background-image: url('../src/assets/img/bg9.jpg');"
    loading="lazy">
    <span class="mask bg-gradient-dark opacity-6"></span>
    <div class="container my-auto">
      <div class="row">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">

            <div class="card-body">
              <h3 class="text-center mb-4">User Information</h3>
              <!-- <form name="signUpInfo" role="form" class="text-start"> -->
              <fieldset disabled>

                <div class="row">
                  <div class="col-md-6">
                    <div class="col-md-6 input-group input-group-outline mb-4">
                      <div style="display: flex; flex-direction: column; width: 100%;">
                        <label for="disabledTextInput">First Name</label>
                        <input v-model="firstName" class="form-control" placeholder="First Name" type="text"
                          id="disabledTextInput">
                      </div>
                    </div>
                  </div>

                  <div class="col-md-6">
                    <div class="input-group input-group-outline mb-4">
                      <div style="display: flex; flex-direction: column; width: 100%;">
                        <label>Last Name</label>
                        <input v-model="lastName" class="form-control" placeholder="Last Name" type="text">
                      </div>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="input-group input-group-outline mb-4">
                    <div style="display: flex; flex-direction: column; width: 100%;">
                      <label>Email Address</label>
                      <input v-model="emailAddress" class="form-control" placeholder="Email Address" type="text">
                    </div>
                  </div>
                </div>

                <!-- <div class="row">
                  <div class="input-group input-group-outline mb-4">
                    <div style="display: flex; flex-direction: column; width: 100%;">
                      <label>Password</label>
                      <input v-model="password" class="form-control" placeholder="Password" type="password">
                    </div>
                  </div>
                </div> -->

                <div class="row">
                  <div class="input-group input-group-outline mb-4">
                    <div style="display: flex; flex-direction: column; width: 100%;">
                      <label>Initial</label>
                      <input v-model="initial" class="form-control" placeholder="eg. JH" type="text">
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-12">
                    <div class="input-group input-group-static mb-2">
                      <label for="favcolor">Your favorite color</label>
                    </div>
                    <div class="input-group input-group-static ">
                      <input v-model="color" type="color" id="favcolor" name="favcolor" value="#000000">
                    </div>
                  </div>
                </div>

              </fieldset>

              <div class="text-center">
                <button @click="moveToEditPage" class="btn bg-gradient-info w-100 my-4 mb-2">edite</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


  <div>
    <p v-if="!dataList">로딩...</p>
    <div v-else>
      <div v-for="(diary, index) in dataList" :key="index">
        <h2>Data {{ index + 1 }}</h2>
        <pre>{{ diary }}</pre>
      </div>
    </div>
  </div>
</template>

<style>
#favcolor {
  width: 50px;
  height: 50px;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  background-color: transparent;
  border: none;
}

#favcolor::-webkit-color-swatch {
  border-radius: 30px;
  border: none;
}
</style>
