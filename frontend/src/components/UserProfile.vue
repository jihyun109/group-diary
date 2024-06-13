<script>
export default {
  props: {
    // userData: Object,

    color: {
      type: String,
      default: 'blue',
    },
    firstName: {
      type: String,
      default: '',
    },
    lastName: {
      type: String,
      default: '',
    }
  }, 

  computed: {
    circleStyle() {
      return {
        width: `50px`,
        height: `50px`,
        backgroundColor: this.color,
        borderRadius: '50%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        color: 'black',
        fontSize: `20px`,
        textAlign: 'center',
      };
    }
  },

  methods: {
    getInitials(name){
      if (this.isEnglish(name)) {
        const firstNameInitial = this.firstName.substring(0, 1).toUpperCase();
        const lastNameInitial = this.lastName.substring(0, 1).toUpperCase();
        return firstNameInitial + lastNameInitial;
      } else {
        return name.substring(0, 2).toUpperCase();
      }
    },

    isEnglish(name) {
      const englishRegex = /^[A-Za-z\s]+$/;
      return englishRegex.test(name);
    }
  }
};
</script>

<template>
    <div class="circle" :style="circleStyle">
              {{ getInitials(firstName) }}
    </div>
</template>