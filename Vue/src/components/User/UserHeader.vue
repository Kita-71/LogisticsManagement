<template>
  <div class="header">
    <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="rgba(0,0,0,0)"
        text-color="#000"
        active-text-color="#f2ee8d">
      <el-menu-item index="1">主页</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的物流</template>
        <el-menu-item index="2-1">物流订单管理</el-menu-item>
        <el-menu-item index="2-2">预约发货</el-menu-item>
      </el-submenu>
    </el-menu>
    <div class="logo" >
      <img src ="@/assets/logo.png">
    </div>
    <div class="user">
      <el-dropdown >
        <i class="el-icon-setting" ></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人信息</el-dropdown-item>
          <el-dropdown-item>加盟</el-dropdown-item>
          <el-dropdown-item>退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>登录人名称</span>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserHeader",
  data(){
    return{
      activeIndex: '1',
      activeIndex2: '1',
    }
  },
  methods:{
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
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