import { createRouter, createWebHistory } from "vue-router";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/logIn'  
    },
    {
      path: "/diaryDetail",
      name: "diaryDetail",
      component: () => import("../views/diaries/diaryDetailPage.vue"),
    },
    {
      path: "/editDiary",
      name: "editDiary",
      component: () => import("../views/diaries/writeDiaryPage.vue"),
      props: true,
      // props: route => ({ diaryData: route.params.diaryData })
    },
    {
      path: "/main",
      name: "main",
      component: () => import("../views/diaries/mainPage.vue"),
    },
    {
      path: "/writeDiary",
      name: "writeDiary",
      component: () => import("../views/diaries/writeDiaryPage.vue"),
    },
    {
      path: "/createTeam",
      name: "createTeam",
      component: () => import("../views/teams/createTeamPage.vue"),
    },
    {
      path: "/teamPage",
      name: "teamPage",
      component: () => import("../views/teams/teamPage.vue"),
    },
    {
      path: "/signUp",
      name: "signUp",
      component: () => import("../views/users/signUpPage.vue"),
    },
    {
      path: "/logIn",
      name: "logIn",
      component: () => import("../views/users/logInPage.vue"),
    },
    {
      path: "/userInfoEdit",
      name: "userInfoEdit",
      component: () => import("../views/users/userInfoEditPage.vue"),
    },
    {
      path: "/userInfo",
      name: "userInfo",
      component: () => import("../views/users/userInfoPage.vue"),
    },
  ],
});

export default router;
