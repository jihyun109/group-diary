<script>
export default {
  name: 'cancelModal',
  props: {
    message: {
      type: String,
      required: true,
      default: '' // 기본값을 설정합니다
    }
  }, 
  methods: {
    goBack() {
      this.$router.back();
    }
  }
};
</script>

<template>
    <div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="canceleModalLabel"
        aria-hidden="true" data-bs-backdrop="false" data-bs-keyboard="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="canceleModalLabel">회원가입 취소</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    {{ message }} 을 취소하시겠습니까?
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn bg-gradient-dark mb-0" data-bs-dismiss="modal">Close</button>
                    <a class="btn btn-primary bg-gradient-info mb-0" @click="goBack" role="button">Yes</a>
                </div>
            </div>
        </div>
    </div>
</template>