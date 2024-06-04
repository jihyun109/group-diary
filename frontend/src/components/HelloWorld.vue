<script>
export default {
  mounted() {
  },
  data() {
    return {
      menuList: [
        {
          name: "diaryDetail",
          icon: "star",
          url: "/diaryDetail"
        },
        {
          name: "editDiary",
          icon: "star",
          url: "/editDiary"
        },
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
        {
          name: "createTeam",
          icon: "star",
          url: "/createTeam"
        },
        {
          name: "team",
          icon: "star",//https://fonts.google.com/icons?selected=Material+Symbols+Outlined:star:FILL@0;wght@400;GRAD@0;opsz@24&icon.size=24&icon.color=%235f6368
          url: "/team"
        },
        {
          name: "logIn",
          icon: "star",//https://fonts.google.com/icons?selected=Material+Symbols+Outlined:star:FILL@0;wght@400;GRAD@0;opsz@24&icon.size=24&icon.color=%235f6368
          url: "/logIn"
        },
        {
          name: "signUp",
          icon: "star",//https://fonts.google.com/icons?selected=Material+Symbols+Outlined:star:FILL@0;wght@400;GRAD@0;opsz@24&icon.size=24&icon.color=%235f6368
          url: "/signUp"
        },
        // 초과되면 처리가 이상해짐 ,, ㅡㅡ
        // {
        //   name: "userInfoEdit",
        //   icon: "star",//https://fonts.google.com/icons?selected=Material+Symbols+Outlined:star:FILL@0;wght@400;GRAD@0;opsz@24&icon.size=24&icon.color=%235f6368
        //   url: "/userInfoEdit"
        // },
        // {
        //   name: "userInfo",
        //   icon: "star",//https://fonts.google.com/icons?selected=Material+Symbols+Outlined:star:FILL@0;wght@400;GRAD@0;opsz@24&icon.size=24&icon.color=%235f6368
        //   url: "/userInfo"
        // },

      ]
    }
  },
  methods: {
  }

}
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
    <div class="container">
      <a class="navbar-brand" href="/main" rel="tooltip"
        title="Designed and Coded by Creative Tim" data-placement="bottom">
        지현 다이어리
      </a>

      <div class="container navbar-collapse  pt-3 pb-2 py-lg-0 ms-lg-12 ps-lg-5" >
        <ul class="navbar-nav navbar-nav-hover ms-auto" v-for="{ name, url,icon }, idx in menuList" :key="idx">
          <li class="nav-item dropdown dropdown-hover mx-2 ms-lg-5">
            <a class="nav-link ps-2 d-flex justify-content-between cursor-pointer align-items-center"
            :href=url aria-expanded="false">
              <i class="material-icons opacity-6 me-2 text-md">{{icon}}</i>
              {{ name }}

            </a>

          </li>
          
        </ul>
      </div>
    </div>
  </nav>


</template>
