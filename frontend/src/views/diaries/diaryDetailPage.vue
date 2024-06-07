  <script>
  import { mapState } from 'vuex';

  export default {
    mounted() {
      this.fetchData();
      this.$store.dispatch('fetchData');
    },

    computed: {
      ...mapState(['userId']),
    },

    data() {
      return {
        diary: null,
      }
    },
    methods: {
      async fetchData() {
        this.diary = null
        // 'res' 변수에는 HTTP 응답 객체가 담김.
        const res = await fetch(`http://localhost:8080/diaries/details/${this.$route.query.diary}`)
        // fetch 메서드로부터 받은 HTTP 응답을 JSON 형식으로 해석하는 작업을 수행
        const resBody = await res.json()
        this.diary = resBody.data
        // 질문: 왜 콘솔창에 todoData가 출력이 안되는지
        console.log(this.diary)
      },
      formattedDate(diary) {
        if (diary.written_date) {
          const date = diary.written_date;
          return `작성일: ${diary.written_date}`;
        }
        return '';
      },
      editDiary() {
        console.log(this.diary,"detail")
        this.$router.push({ name: 'editDiary', query: { diaryId: this.diary.id } })

      },

      async requestDeleteDiary() {
        try {
          const response = await fetch(`http://localhost:8080/diaries/${this.$route.query.diary}`, {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json'
            }
          });
          if (response.ok) {
            alert('삭제되었습니다.');
            window.history.back();
          } else {
            console.error('Error deleting diary');
          }
        } catch (error) {
          console.error('Error:', error);
        }
      }


    }

  }
</script>
  
  <template>

    <p v-if="!diary">로딩...</p>
    <div v-else>
      <div class="container mt-5">
        <div v-if="diary" class="card">
          <div class="card-header d-flex justify-content-between align-items-center">
            <div>
              <h3>{{ diary.diary_title }}</h3>
              <small>{{ formattedDate(diary) }}</small>
            </div>
            <div v-if="diary.writer_id === this.userId">
              <button class="btn btn-primary btn-sm" @click="editDiary">수정</button>
              <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal">삭제</button>
            </div>
          </div>
          <div class="card-body">
            <p>{{ diary.details }}</p>
          </div>
          <div class="card-footer">
          </div>
        </div>
      </div>
    </div>

    <!-- 삭제  -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true"
      data-bs-backdrop="false" data-bs-keyboard="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="deleteModalLabel">일기 삭제</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            일기를 삭제하시겠습니까?
          </div>
          <div class="modal-footer justify-content-between">
            <button type="button" class="btn bg-gradient-dark mb-0" data-bs-dismiss="modal">Close</button>
            <a class="btn btn-primary bg-gradient-info mb-0" @click="requestDeleteDiary" role="button">Yes</a>
          </div>
        </div>
      </div>
    </div>
  </template>
