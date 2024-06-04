<script>
export default {
  mounted() {
    this.fetchData()
  },
  data() {
    return {
      dataList: []
    }
  },
  methods: {
    async fetchData() {
        const urls = [
        `http://localhost:8080/teams`,
        `http://localhost:8080/teamDiaries`,
        `http://localhost:8080/members`,
        `http://localhost:8080/users`,
        `http://localhost:8080/diaries`
        ];

        const requests = urls.map(async url => {
            const res = await fetch(url);
            return res.json();
        });

        this.dataList = await Promise.all(requests);
        console.log(this.dataList);
    }
  }
}
</script>

<template>
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
