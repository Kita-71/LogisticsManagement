<template>
  <div class="header">
    <div class="user">
      <el-dropdown  style=" color: #999922; position: relative; left: 60px;top: -16px;">
        <div style="font-size: 45px; ">

          <i class="el-icon-setting"  ></i>
          <el-dropdown-menu slot="dropdown"  >
            <el-dropdown-item  @click.native="Exit">退出</el-dropdown-item>
          </el-dropdown-menu>
        </div>
      </el-dropdown>
    </div>
    <span class="times">{{ time }}</span>
    <div class="user" style="font-size: 25px; position: relative; top: 12px; left: -200px;">
      <i class="el-icon-s-custom" ></i>
      <span>Admin</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      time: ""
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
    },
    Exit(){
      this.$store.commit("admin_exit");
      this.$router.replace({path:'/Sign'});
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
  color: #ffffff;
}

</style>