<template>
  <div class="container mt-5">
    <p v-if="!diary">로딩...</p>
    <div v-else>
      <div class="card mx-auto">
        <div class="card-header text-center">
          <h1>{{ diary.diary_title }}</h1>
        </div>
        <div class="card-body">
  <div class="d-flex justify-content-between align-items-center">
    <div class="text-start">
      작성자: {{ diary.last_name }} {{ diary.first_name }} <br>
      {{ formattedDate(diary) }}
    </div>
  </div>
  <div class="card mt-4 detail-card">
    <div class="card-body text-start">
      {{ diary.details }}
    </div>
  </div>
</div>

        <div class="card-footer text-end">
          <div v-if="diary.writer_id === this.userId" class="d-flex justify-content-end">
            <button class="btn me-2" @click="editDiary" style="background-color: #638589; color: #ffffff;">수정</button>
            <button class="btn" data-bs-toggle="modal" data-bs-target="#deleteModal" style="background-color: #d1c5ab;">삭제</button>
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
            <a class="btn mb-0" @click="requestDeleteDiary" role="button" style="background-color: #638589; color: #ffffff;">Yes</a>
            <button type="button" class="btn mb-0" data-bs-dismiss="modal" style="background-color: #d1c5ab;">Close</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import UserProfile from '@/components/UserProfile.vue'
import '../../assets/styles.css?v=1.0';

export default {
  components: {
    UserProfile
  },
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
      const res = await fetch(`http://localhost:8080/diaries/details/${this.$route.query.diary}`)
      const resBody = await res.json()
      this.diary = resBody.data
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
      console.log(this.diary, "detail")
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

<style scoped>
.card-body {
  max-width: 700px;
  margin: 0 auto;
}

.card-header, .card-footer {
  text-align: center;
}

.detail-card {
  max-width: 700px;
  margin: 0 auto;
  background-color: #eae3da;
}

.detail-card .card-body {
  text-align: left;
}

</style>
