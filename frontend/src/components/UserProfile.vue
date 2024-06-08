<script>
export default {
  props: {
    userData: Object
  }, 
  methods: {
    getInitials(name){
      return name.substring(0, 2).toUpperCase();
    }
  }
};
</script>

<template>
    <div class="profile-circle" :style="{ backgroundColor: team.color }">
              {{ getInitials(userData.last_name) }}
    </div>
</template>