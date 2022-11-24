<template>
    <el-menu :default-openeds="['1', '3']" class="asideMenu" background-color="#19245f" text-color="#ffffff" :default-active="$route.path" :router="true" >
      <div class="logo">
        <img src="@/assets/logo.png" class="logoimg">
      </div>
      <el-menu-item index="/ManagerHome"  class="mainMenu" >
        <img src="@/assets/Menu1.svg" class="MenuIcon">
        <span slot="title">主页</span>
      </el-menu-item>
      <el-menu-item index="/ManagerUser" class="mainMenu" >
        <img src="@/assets/Menu2.svg" class="MenuIcon">
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/ManagerCourier" class="mainMenu" >
        <img src="@/assets/Menu3.svg" class="MenuIcon">
        <span slot="title">快递点管理</span>
      </el-menu-item>
      <el-menu-item index="/ManagerOrder" class="mainMenu" >
        <img src="@/assets/Menu4.svg" class="MenuIcon">
        <span slot="title">物流订单管理</span>
      </el-menu-item>
      <el-menu-item index="/ManagerJoin" class="mainMenu" >
        <img src="@/assets/Menu1.svg" class="MenuIcon">
        <span slot="title">加盟处理</span>
      </el-menu-item>
    </el-menu>
</template>

<script>
export default {
  name: "ManagerAside",
  data(){
    return{
      menuIndex:1,
    }
  },
  methods:
      {
      },
}
</script>

<style lang="less" scoped>
.logo
{
  position: relative;
  height: 250px;
  width: 260px;
  text-align: center;
  overflow: hidden;
}
.logoimg
{
  width: 300px;
  overflow-x: hidden;
}
.asideMenu
{
  height: 100%;
}
.mainMenu
{
  position: relative;
  font-size: 20px;
  line-height:45px;
  font-family: "PingFang SC";
  margin-top: 20px;
}
.subMenu
{
  height: 40px;
  font-size: 16px;
  line-height: 30px;
  margin-left: 20px;
}
.MenuIcon
{
  width: 30px;
  margin-right: 10px;
}
</style>