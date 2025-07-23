<script>
export default {
  mounted() {
    this.fetchData();
  },
  data() {
    return {
      dataList: [],
    };
  },
  methods: {},
};
</script>

<template>
  <section>
    <div class="container py-4">
      <div class="row">
        <div class="col-lg-7 mx-auto d-flex justify-content-center flex-column">
          <h3 class="text-center">Edit diary</h3>
          <form role="form" id="contact-form" method="post" autocomplete="off">
            <div class="card-body">
              <!-- date picker 사용 -->
              <div class="input-group input-group-static my-3">
                <label>Date</label>
                <input type="date" class="form-control" />
              </div>

              <div class="mb-4">
                <div class="input-group input-group-dynamic">
                  <input type="email" class="form-control" placeholder="제목" />
                </div>
              </div>
              <div class="mb-4">
                <div class="input-group input-group-dynamic">
                  <input
                    type="email"
                    class="form-control"
                    placeholder="팀 추가"
                  />
                  <div class="d-flex justify-content-end">
                    <span class="badge badge-sm bg-gradient-info mx-1"
                      >팀팀팀 <a>x</a></span
                    >
                    <span class="badge badge-sm bg-gradient-secondary mx-1"
                      >팀팀팀 <a>x</a></span
                    >
                    <span class="badge badge-sm bg-gradient-info mx-1"
                      >팀팀팀 <a>x</a></span
                    >
                    <span class="badge badge-sm bg-gradient-info mx-1"
                      >팀팀팀 <a>x</a></span
                    >
                  </div>
                </div>
              </div>
              <div class="input-group mb-4 input-group-static">
                <label>내용</label>
                <textarea
                  name="message"
                  class="form-control"
                  id="message"
                  rows="4"
                ></textarea>
              </div>
              <div class="row">
                <!-- <div class="col-md-12">
                  <div class="form-check form-switch mb-4 d-flex align-items-center">
                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" checked="">
                    <label class="form-check-label ms-3 mb-0" for="flexSwitchCheckDefault">I agree to the <a
                        href="javascript:;" class="text-dark"><u>Terms and Conditions</u></a>.</label>
                  </div>
                </div> -->
                <div class="col-md-12">
                  <button type="submit" class="btn bg-gradient-dark w-100">
                    write diary
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>
