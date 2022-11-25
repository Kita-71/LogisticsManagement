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
            <el-steps class="step" :space="200" :active="this.active" finish-status="success">
              <el-step title="待发出"></el-step>
              <el-step title="运输中"></el-step>
              <el-step title="待签收"></el-step>
            </el-steps>
          </el-col>
          <el-col :span="8" class="col5">
          </el-col>
        </el-row>
        <!-- 第二列栅格布局 -->
        <el-row >
          <el-col class="col1" :span="24">
            <div class="c1">
              <b>订单详细</b>
              <el-descriptions  class="margin-top"direction="vertical" :column="2" >
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-mobile-phone"></i>
                  物流订单号
                </template>
                {{this.orderdata.orderId}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-tickets"></i>
                  对象类型
                </template>
                {{this.orderdata.goods}}
              </el-descriptions-item>
            </el-descriptions>
              <el-descriptions  class="margin-top" direction="vertical" :column="3" >
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-user"></i>寄出人
                </template>
                {{this.orderdata.senderName}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-mobile-phone"></i>
                  寄出人联系方式
                </template>
                {{this.orderdata.senderPhone}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  寄出地址
                </template>
                {{this.orderdata.origin}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-user"></i>收件人
                </template>
                {{this.orderdata.receiverName}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-mobile-phone"></i>
                  收件人联系方式
                </template>
                {{this.orderdata.receiverPhone}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  收件地址
                </template>
                {{this.orderdata.dest}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  下单时间
                </template>
                {{this.orderdata.bookTime}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  寄出时间
                </template>
                {{this.orderdata.sendTime}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-location-outline"></i>
                  签收时间
                </template>
                {{this.orderdata.doneTime}}
              </el-descriptions-item>
            </el-descriptions>
            </div>
          </el-col>
        </el-row>
      </el-main>
  </el-container>
</template>

<script>
import UserHeader from "@/components/User/UserHeader";
import request from "@/utils/request";
export default {
  components: {UserHeader},
  data() {
    return {
      orderdata:{},
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
      }],
      active: 0,
    };
  },
  created() {
    request.get("http://localhost:9090/order/getorder",{params:{orderId:
        this.$router.currentRoute.query.orderId}}).then(res=>{
        this.orderdata=res;
        if(this.orderdata.state=="reserve")
      {
        this.active=0;
      }
      else if(this.orderdata.state=="in_transport")
      {
        this.active=1;
      }
      else if(this.orderdata.state=="pending_pickup")
      {
        this.active=2;
      }
      else
      {
        this.active=3;
      }
    });

  },
  methods:
      {
        goBack()
        {
    this.$router.go(-1);
        }
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
  top:20%;
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
  width: 90%;
  /deep/ .el-descriptions__body
  {
    background-color: transparent;
  }
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