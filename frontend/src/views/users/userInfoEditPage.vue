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
      errors: {
        firstName: false,
        lastName: false,
        emailAddress: false,
        password: false,
        initial: false,
      }
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

    // 사용자 info 데이터 get
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

    // 
    async requestEditeUserInfo() {
      // 오류 상태 초기화
      this.errors.firstName = !this.firstName;
      this.errors.lastName = !this.lastName;
      this.errors.emailAddress = !this.emailAddress;
      this.errors.password = !this.password;
      this.errors.initial = !this.initial;

      // 오류가 있는 경우 경고 메시지 표시
      if (this.errors.firstName || this.errors.lastName || this.errors.emailAddress || this.errors.password || this.errors.initial) {
        alert('모든 필드를 입력해 주세요.');
        return;
      }

      // 입력 데이터를 객체로 수집
      const userData = {
        first_name: this.firstName,
        last_name: this.lastName,
        email: this.emailAddress,
        password: this.password,
        initial: this.initial,
        color: this.color
      };

      try {
        // 서버로 POST 요청 보내기
        const response = await fetch(`http://localhost:8080/users/${this.userId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(userData)
        });

        if (response.ok) {
          // 요청이 성공하면 성공 메시지 표시
          alert('회원 정보가 수정되었습니다.');
          window.history.back();
          this.$store.commit('setFirstName', this.firstName); 
          this.$store.commit('setLastName', this.lastName); 
          this.$store.commit('setEmail', this.emailAddress); 
          this.$store.commit('setPassword', this.password); 
          this.$store.commit('setColor', this.color); 
        } else {
          // 요청이 실패하면 오류 메시지 표시
          const errorData = await response.json();
          alert(`오류가 발생했습니다: ${errorData.message}`);
        }
      } catch (error) {
        // 네트워크 오류 처리
        alert(`네트워크 오류가 발생했습니다: ${error.message}`);
      }
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
              <h3 class="text-center mb-4">Edite information</h3>
              <form name="userInfo" role="form" class="text-start">

                <div class="row">
                  <div class="col-md-6">
                    <div class="col-md-6 input-group input-group-outline mb-4"
                      :class="{ 'is-invalid': errors.firstName }">
                      <div style="display: flex; flex-direction: column; width: 100%;">
                        <label>First Name</label>
                        <input v-model="firstName" class="form-control" placeholder="First Name" type="text">
                      </div>
                    </div>
                  </div>

                  <div class="col-md-6">
                    <div class="input-group input-group-outline mb-4" :class="{ 'is-invalid': errors.lastName }">
                      <div style="display: flex; flex-direction: column; width: 100%;">
                        <label>Last Name</label>
                        <input v-model="lastName" class="form-control" placeholder="Last Name" type="text">
                      </div>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="input-group input-group-outline mb-4" :class="{ 'is-invalid': errors.emailAddress }">
                    <div style="display: flex; flex-direction: column; width: 100%;">
                      <label>Email Address</label>
                      <input v-model="emailAddress" class="form-control" placeholder="Email Address" type="text">
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="input-group input-group-outline mb-4" :class="{ 'is-invalid': errors.password }">
                    <div style="display: flex; flex-direction: column; width: 100%;">
                      <label>Password</label>
                      <input v-model="password" class="form-control" placeholder="Password" type="password">
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="input-group input-group-outline mb-4" :class="{ 'is-invalid': errors.firstName }">
                    <div style="display: flex; flex-direction: column; width: 100%;">
                      <label>Initial</label>
                      <input v-model="initial" class="form-control" placeholder="eg. JH" type="text">
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-12">
                    <div class="input-group input-group-static mb-2">
                      <label for="favcolor">Select your favorite color</label>
                    </div>
                    <div class="input-group input-group-static ">
                      <input v-model="color" :class="{ 'is-invalid': errors.firstName }" type="color" id="favcolor"
                        name="favcolor" value="#000000">
                    </div>
                  </div>
                </div>

                <div class="text-center">
                  <button @click.prevent="requestEditeUserInfo" class="btn bg-gradient-info w-100 my-4 mb-2">Save</button>
                  <!-- Button trigger modal -->
                  <button type="button" class="btn bg-btn-primary bg-gradient-light w-100 mb-2" data-bs-toggle="modal"
                    data-bs-target="#editeCanceleModal">
                    Cancel
                  </button>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="editeCanceleModal" tabindex="-1" aria-labelledby="editeCanceleModalLabel"
                  aria-hidden="true" data-bs-backdrop="false" data-bs-keyboard="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="editeCanceleModalLabel">회원정보 수정 취소</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        회원 정보 수정을 취소하시겠습니까?
                      </div>
                      <div class="modal-footer justify-content-between">
                        <button type="button" class="btn bg-gradient-dark mb-0" data-bs-dismiss="modal">Close</button>
                        <a class="btn btn-primary bg-gradient-info mb-0" href="userInfo" role="button">Yes</a>
                      </div>
                    </div>
                  </div>
                </div>

              </form>
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
                <pre>{{  diary }}</pre>
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
