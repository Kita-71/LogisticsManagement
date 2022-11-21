<template>
  <div class="header">
    <span class="times">{{ time }}</span>
    <div class="user">
      <i class="el-icon-s-custom" ></i>
      <span>Admin</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      time: "2020年08月31日 10:29"
    };
  },
  mounted() {
    this.currentTime();
  },
  methods: {
    //   时间
    currentTime() {
      setInterval(this.getTime, 500);
    },
    getTime() {
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth() + 1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf =
          new Date().getMinutes() < 10
              ? "0" + new Date().getMinutes()
              : new Date().getMinutes();
      let ss =
          new Date().getSeconds() < 10
              ? "0" + new Date().getSeconds()
              : new Date().getSeconds();
      this.time =
          yy + "年 " + mm + "月" + dd + "日 " + hh + ":" + mf + ":" + ss;
    }
  }
}
</script>

<style scoped>
.header{
  width: 100%;
  height: 100%;
  background-color: #4d52f8;
}
.times{
  position: absolute;
  left:50%;
  top:2%;
  color: #ffffff;
}
.user
{
  position: absolute;
  left:90%;
  top:2%;
}

</style>