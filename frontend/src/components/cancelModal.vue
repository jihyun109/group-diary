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
                    <h5 class="modal-title" id="canceleModalLabel">{{message}} 취소</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="background-color: #638589; color: #ffffff;"></button>
                </div>
                <div class="modal-body">
                    {{ message }} 을 취소하시겠습니까?
                </div>
                <div class="modal-footer justify-content-between">
                  <a class="btn mb-0" @click="goBack" role="button" style="background-color: #638589; color: #ffffff;">Yes</a>
                    <button type="button" class="btn mb-0" data-bs-dismiss="modal" style="background-color: #d1c5ab;">Close</button>
                    
                </div>
            </div>
        </div>
    </div>
</template>