// import './assets/main.css'

import { createApp } from 'vue';
import { createPinia } from 'pinia';
import 'es6-promise/auto';
import App from './App.vue';
import router from './router';
import store from './stores/store';

// Bootstrap 초기화 및 Popper.js 경고 해결
if (typeof window !== 'undefined') {
  // Bootstrap 드롭다운 초기화 시 Popper 옵션 설정
  document.addEventListener('DOMContentLoaded', () => {
    // 모든 드롭다운에 대해 Popper 옵션 설정
    const dropdowns = document.querySelectorAll('.dropdown-toggle')
    dropdowns.forEach(dropdown => {
      dropdown.addEventListener('click', (e) => {
        // Popper 경고 방지를 위한 지연 처리
        setTimeout(() => {
          const dropdownMenu = dropdown.nextElementSibling
          if (dropdownMenu && dropdownMenu.classList.contains('dropdown-menu')) {
            dropdownMenu.style.transition = 'opacity 0.15s ease-in-out, visibility 0.15s ease-in-out'
          }
        }, 0)
      })
    })
  })
}

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.use(store);

app.mount('#app');
