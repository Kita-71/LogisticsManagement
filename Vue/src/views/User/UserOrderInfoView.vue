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
            <el-page-header class="pageheader" @back="goBack" content="详情页面">
            </el-page-header>
          </el-col>
          <el-col :span="8" class="col4">
            <el-steps class="step" :space="200" :active="1" finish-status="success">
              <el-step title="已完成"></el-step>
              <el-step title="进行中"></el-step>
              <el-step title="步骤 3"></el-step>
            </el-steps>
          </el-col>
          <el-col :span="8" class="col5">
          </el-col>
        </el-row>
        <!-- 第二列栅格布局 -->
        <el-row :gutter="20">
          <el-col class="col1" :span="16">
            <div class="c1">
              <b>订单详细</b>
              <el-descriptions  class="margin-top"direction="vertical" :column="2" :size="medium">
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-mobile-phone"></i>
                  物流订单号
                </template>
                BabyQ18100000923
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  对象类型
                </template>
                日用品
              </el-descriptions-item>
            </el-descriptions>
              <el-descriptions  class="margin-top" direction="vertical" :column="3" :size="medium">
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-user"></i>寄出人
                </template>
                Kita
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-mobile-phone"></i>
                  寄出人联系方式
                </template>
                13126078008
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  寄出地址
                </template>
                苏州市
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-user"></i>收件人
                </template>
                Hokiya
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-mobile-phone"></i>
                  收件人联系方式
                </template>
                13981965263
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  收件地址
                </template>
                苏州市
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  下单时间
                </template>
                2022-5-20
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  寄出时间
                </template>
                2022-8-14
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  签收时间
                </template>
                2022-11-7
              </el-descriptions-item>
            </el-descriptions>
            </div>
          </el-col>
          <el-col class="col2" :span="8">
            <div class="c2">
              <b class="b1">物流跟踪</b>
            <div class="block">
              <el-timeline :reverse="reverse" :color="'#0bbd87'">
                <el-timeline-item
                    v-for="(activity, index) in activities"
                    :key="index"
                    :timestamp="activity.timestamp">
                  {{activity.content}}
                </el-timeline-item>
              </el-timeline>
            </div>
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
  left: 14.5% ;
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
.el-descriptions
  {
  position: relative;
  left: 5%;
  top:60px;
  background-color:none;
  width: 90%;
  /deep/ .el-descriptions__body
  {
    background-color: transparent;
  }
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