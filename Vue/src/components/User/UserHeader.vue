<template>
  <div class="header">
    <el-menu
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="rgba(0,0,0,0)"
        text-color="#000"
        active-text-color="#f2ee8d"
        :default-active="$route.path"
        :router="true">
      <el-menu-item index="/UserHome" >主页</el-menu-item>
      <el-submenu index="/UserOrder">
        <template slot="title" >我的物流</template>
        <el-menu-item index="/UserOrder" >我的订单</el-menu-item>
        <el-menu-item index="/UserBook">预约发货</el-menu-item>
      </el-submenu>
    </el-menu>
    <div class="logo" >
      <img src ="@/assets/logo.png">
    </div>
    <div class="user">
      <el-dropdown style="  position: relative; left: 60px;top: 6px;">
        <div style="font-size: 45px; ">

          <i class="el-icon-setting"  ></i>
          <el-dropdown-menu slot="dropdown"  >
            <el-dropdown-item @click.native="lookUserInfo">个人信息</el-dropdown-item>
            <el-dropdown-item  @click.native="Exit">退出</el-dropdown-item>
          </el-dropdown-menu>
        </div>
      </el-dropdown>
      <div style="font-size: 25px; position: relative; top: -60px; left: -120px;">
        <i class="el-icon-s-custom" ></i>
        <span>Admin</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserHeader",
  data(){
    return{
      activeIndex: '1',
      userdata:[],
    }
  },
  created() {
      this.request.get("http://localhost:9090/user/get",{params:{username:this.$store.state.user.username}})
          .then(res=>
          {
            console.log(res);
            this.userdata=res;
          })
  },
  methods:{
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    lookUserInfo(){
      this.$router.push({path:'/UserInfo'});
    },
    Join(){
      this.$router.push({path:'/UserJoin'});
    },
    Exit(){
      this.$store.commit("user_exit");
      this.$router.replace({path:'/Sign'});
    }
  }
}

</script>

<style lang="less" scoped>
.header
{
  font-size: 12px;background-image:  linear-gradient(270deg, 	rgb(245,247,250,0.1) 1%, 	rgba(195,207,226,0.1) 20%),linear-gradient(225deg, #69EACB 0%, #EACCF8 48%, #6654F1 100%);display: flex;line-height: 60px;
  .logo {
    position: absolute;
    top:-10px;
    left: 50%;
    transform: translate(-50%,0);
    img {
      width: 100px;
    }
  }
}
.el-menu-item.is-active{
  color:#f2ee8d !important;
  border-bottom-color: transparent !important;
}
.el-menu-item a:hover {
  background: none;
}
//二次菜单悬浮及背景样式
.el-menu--popup-bottom-start .el-menu-item:hover{
  color: #f2ee8d !important;
}
.el-menu--popup-bottom-start .el-menu-item{
  background: #fff !important;
}


.user
{
  position: absolute;
  left: 90%;
.el-dropdown
{
  margin-right: 15px;color: #000;
}
.span
{
  color: #000;
}

}
</style>