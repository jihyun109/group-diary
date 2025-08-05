<script>
import { signUpUser } from '../../api/user.js';
export default {
  data() {
    return {
      dataList: [],
      firstName: '',
      lastName: '',
      emailAddress: '',
      password: '',
      color: '',
      errors: {
        firstName: false,
        lastName: false,
        emailAddress: false,
        password: false,
      },
    };
  },
  methods: {
    async signUp() {
      // 오류 상태 초기화
      this.errors.firstName = !this.firstName;
      this.errors.lastName = !this.lastName;
      this.errors.emailAddress = !this.emailAddress;
      this.errors.password = !this.password;

      // 오류가 있는 경우 경고 메시지 표시
      if (
        this.errors.firstName ||
        this.errors.lastName ||
        this.errors.emailAddress ||
        this.errors.password
      ) {
        alert('모든 필드를 입력해 주세요.');
        return;
      }

      // 입력 데이터를 객체로 수집
      const signUpData = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.emailAddress,
        password: this.password,
        color: this.color,
      };

      try {
        await signUpUser(signUpData);
        alert('회원가입이 완료되었습니다.');
        this.$router.push({ path: '/logIn' });
      } catch (error) {
        alert(`오류가 발생했습니다: ${error.message}`);
      }
    },
  },
};
</script>

<template>
  <div
    class="page-header align-items-start min-vh-100"
    style="background-image: url('../src/assets/img/LogInBG.jpg')"
    loading="lazy"
  >
    <span class="mask opacity-6"></span>
    <div class="container my-auto">
      <div class="row">
        <div class="col-lg-4 col-md-8 col-12 mx-auto">
          <div class="card z-index-0 fadeIn3 fadeInBottom">
            <div class="card-body">
              <h3 class="text-center mb-4">Sign up</h3>
              <form name="signUpInfo" role="form" class="text-start">
                <div class="row">
                  <div class="col-md-6">
                    <div
                      class="col-md-6 input-group input-group-outline mb-4"
                      :class="{ 'is-invalid': errors.firstName }"
                    >
                      <div
                        style="
                          display: flex;
                          flex-direction: column;
                          width: 100%;
                        "
                      >
                        <label>First Name</label>
                        <input
                          v-model="firstName"
                          class="form-control"
                          placeholder="First Name"
                          type="text"
                        />
                      </div>
                    </div>
                  </div>

                  <div class="col-md-6">
                    <div
                      class="input-group input-group-outline mb-4"
                      :class="{ 'is-invalid': errors.lastName }"
                    >
                      <div
                        style="
                          display: flex;
                          flex-direction: column;
                          width: 100%;
                        "
                      >
                        <label>Last Name</label>
                        <input
                          v-model="lastName"
                          class="form-control"
                          placeholder="Last Name"
                          type="text"
                        />
                      </div>
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div
                    class="input-group input-group-outline mb-4"
                    :class="{ 'is-invalid': errors.emailAddress }"
                  >
                    <div
                      style="display: flex; flex-direction: column; width: 100%"
                    >
                      <label>Email Address</label>
                      <input
                        v-model="emailAddress"
                        class="form-control"
                        placeholder="Email Address"
                        type="text"
                      />
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div
                    class="input-group input-group-outline mb-4"
                    :class="{ 'is-invalid': errors.password }"
                  >
                    <div
                      style="display: flex; flex-direction: column; width: 100%"
                    >
                      <label>Password</label>
                      <input
                        v-model="password"
                        class="form-control"
                        placeholder="Password"
                        type="password"
                      />
                    </div>
                  </div>
                </div>

                <!-- <div class="row">
                  <div class="input-group input-group-outline mb-4" :class="{ 'is-invalid': errors.firstName }">
                    <div style="display: flex; flex-direction: column; width: 100%;">
                      <label>Initial</label>
                      <input v-model="initial" class="form-control" placeholder="eg. JH" type="text">
                    </div>
                  </div>
                </div> -->

                <div class="row">
                  <div class="col-md-12">
                    <div class="input-group input-group-static mb-2">
                      <label for="favcolor">Select your favorite color</label>
                    </div>
                    <div class="input-group input-group-static">
                      <input
                        v-model="color"
                        :class="{ 'is-invalid': errors.firstName }"
                        type="color"
                        id="favcolor"
                        name="favcolor"
                        value="#000000"
                      />
                    </div>
                  </div>
                </div>

                <div class="text-center">
                  <button
                    @click.prevent="signUp"
                    class="btn w-100 my-4 mb-2"
                    style="background-color: #728462; color: #ffffff"
                  >
                    Sign up
                  </button>
                  <!-- Button trigger modal -->
                  <button
                    type="button"
                    class="btn w-100 mb-2"
                    data-bs-toggle="modal"
                    data-bs-target="#signUpCanceleModal"
                    style="background-color: #ece5d3"
                  >
                    Cancel
                  </button>
                </div>

                <!-- Modal -->
                <div
                  class="modal fade"
                  id="signUpCanceleModal"
                  tabindex="-1"
                  aria-labelledby="signUpCanceleModalLabel"
                  aria-hidden="true"
                  data-bs-backdrop="false"
                  data-bs-keyboard="true"
                >
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="signUpCanceleModalLabel">
                          회원가입 취소
                        </h5>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body">회원가입을 취소하시겠습니까?</div>
                      <div class="modal-footer justify-content-between">
                        <a
                          class="btn mb-0"
                          href="/logIn"
                          role="button"
                          style="background-color: #638589; color: #ffffff"
                          >Yes</a
                        >
                        <button
                          type="button"
                          class="btn mb-0"
                          data-bs-dismiss="modal"
                          style="background-color: #d1c5ab"
                        >
                          Close
                        </button>
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

body {
  background-color: #afbda4 !important;
}

.card {
  margin-top: 30px;
}
</style>
