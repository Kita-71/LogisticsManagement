<template>
    <el-menu :default-openeds="['1', '3']" class="asideMenu" background-color="#19245f" text-color="#ffffff">
      <div class="logo">
        <img src="@/assets/logo.png" class="logoimg">
      </div>
      <el-menu-item index="1"  class="mainMenu">
        <img src="@/assets/Menu1.svg" class="MenuIcon">
        <span slot="title">主页</span>
      </el-menu-item>
      <el-menu-item index="2" class="mainMenu">
        <img src="@/assets/Menu2.svg" class="MenuIcon">
        <span slot="title">信息管理</span>
      </el-menu-item>
      <el-menu-item index="2-1" class="subMenu">用户管理</el-menu-item>
      <el-menu-item index="2-2" class="subMenu">快递点管理</el-menu-item>
      <el-menu-item index="2-3" class="subMenu">物流订单管理</el-menu-item>
    </el-menu>
</template>

<script>
export default {
  name: "ManagerAside"
}
</script>

<style lang="less" scoped>
.logo
{
  position: relative;
  height: 250px;
  width: 260px;
  text-align: center;
}
.logoimg
{
  width: 300px;
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