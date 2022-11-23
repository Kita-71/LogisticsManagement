<template>
  <el-container class="container1">
    <!-- 顶栏 -->
    <el-header height="60px">
      <UserHeader></UserHeader>
    </el-header>
    <el-main>
      <!-- 第一列布局 -->
      <el-row >
        <el-col :span="8" class="col3">
          <el-page-header class="pageheader" @back="goBack" content="预约发货">
          </el-page-header>
        </el-col>
        <el-col :span="8" class="col4">
        </el-col>
        <el-col :span="8" class="col5">
        </el-col>
      </el-row>
      <!-- 第二列栅格布局 -->
      <el-row :gutter="20">
        <el-col class="col1" :span="16">
          <div class="c1">
            <b>加盟进度</b>
            <el-steps class="step" :space="200" :active="1" finish-status="success">
              <el-step title="已完成"></el-step>
              <el-step title="进行中"></el-step>
              <el-step title="步骤 3"></el-step>
            </el-steps>
          </div>
        </el-col>
        <el-col class="col2" :span="8">
          <div class="c2">
            <b class="b1">已预约</b>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import UserHeader from "@/components/User/UserHeader";
export default {
  components:{UserHeader},
  data() {
    return {
      reverse: true,
      activities: [{
        content: '到达xxx地',
        timestamp: '2022-05-19'
      }, {
        content: '到达xxx地',
        timestamp: '2022-04-13'
      }, {
        content: '到达xxx地',
        timestamp: '2022-04-11'
      }]
    };
  }
}
</script>

<style lang="less" scoped>
.step
{
  position: relative;
  top: 10%;
  left: 25% ;
}
.pageheader
{
  width: 200px;
}
.container1{
  height: 100%;
}
.el-header {
  padding: 0px;
}
.el-main {
  background-image: linear-gradient(0deg, 	rgb(245,247,250,0.1) 0%, 	rgba(195,207,226,0.1) 70%),linear-gradient(-225deg, #5271C4 0%, #B19FFF 48%, #ECA1FE 100%);
  height: 100%;
}
.el-col {
  height: 200px;
}

.c1
{
  position: relative;
  top:10%;
  left: 2.5%;
  background-image: linear-gradient(-225deg,rgba(255,255,255,0.5) 0%,rgba(255,255,255,0.5) 100%);
  border-radius: 30px;
  width: 95%;
  height: 650px;
  b
  {
    font-size: 18px;
    position: relative;
    top: 30px;
    left: 40px;
  }
}

.c2
{
  position: relative;
  top:10%;
  left: 2.5%;
  background-image: linear-gradient(-225deg,rgba(255,255,255,0.5) 0%,rgba(255,255,255,0.5) 100%);
  border-radius: 30px;
  width: 95%;
  height: 650px;
  b
  {
    font-size: 18px;
    position: relative;
    top: 30px;
    left: 40px;
  }
  .block
  {
    position: relative;
    top: 110px;
    left: 40px;
  }
}
.col1 {
}
.col2 {
}
.col3 {
  height: 40px;
}
.col4 {
  height: 40px;
}
.col5 {
  height: 40px;
}
</style>