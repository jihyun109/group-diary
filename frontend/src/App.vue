<script setup>
import {  RouterView } from 'vue-router'
import HeaderMenu from './components/HeaderComponent.vue'
// const route = useRoute();
</script>

<template>
  
  <HeaderMenu />
  <!-- <RouterView style="height: 1024px;"/> -->
  <div class="content">
    <RouterView />
  
</div>
</template>


<style>
/* 헤더의 높이만큼 마진 추가 */
.content {
  margin-top: 100px; /* 예: 헤더의 높이가 60px인 경우 */
}
</style>
