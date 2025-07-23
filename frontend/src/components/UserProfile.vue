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
    },
    width: {
      type: Number,
      default: '50',
    },
    height: {
      type: Number,
      default: '50',
    },
    fontSize: {
      type: Number,
      default: '20',
    },
  },

  computed: {
    circleStyle() {
      return {
        width: `${this.width}px`,
        height: `${this.height}px`,
        backgroundColor: this.color,
        borderRadius: '50%',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        color: 'black',
        fontSize: `${this.fontSize}px`,
        textAlign: 'center',
      };
    },
  },

  methods: {
    getInitials(name) {
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
    },
  },
};
</script>

<template>
  <div class="circle" :style="circleStyle">
    {{ getInitials(firstName) }}
  </div>
</template>
