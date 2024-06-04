  <script>
  export default {
    mounted() {
      this.fetchData()
    },
    data() {
      return {
        diariesList: null
      }
    },
    methods: {
      async fetchData() {
        this.diariesList = null
        // 'res' 변수에는 HTTP 응답 객체가 담김.
        const res = await fetch(`http://localhost:8080/diaries`)
        // fetch 메서드로부터 받은 HTTP 응답을 JSON 형식으로 해석하는 작업을 수행
        this.diariesList = await res.json()
        // 질문: 왜 콘솔창에 todoData가 출력이 안되는지
        console.log(this.diariesList)
      }
    }
    
  }
  </script>
  
  <template>
    <p v-if="!diariesList">로딩...</p>
    <pre v-else>{{ diariesList }}</pre>
  </template>
  