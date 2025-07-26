<script>
const BASE_URL = import.meta.env.VITE_API_BASE_URL;
export default {
  mounted() {
    this.fetchData();
  },
  data() {
    return {
      dataList: [],
    };
  },
  methods: {
    async fetchData() {
      const urls = [`${BASE_URL}/teams`, `${BASE_URL}/members`];

      const requests = urls.map(async (url) => {
        const res = await fetch(url);
        return res.json();
      });

      this.dataList = await Promise.all(requests);
      console.log(this.dataList);
    },
  },
};
</script>

<template>
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
