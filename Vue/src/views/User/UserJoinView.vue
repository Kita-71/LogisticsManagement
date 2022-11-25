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
          <el-page-header class="pageheader" @back="goBack" content="加盟">
          </el-page-header>
        </el-col>
        <el-col :span="8" class="col4">
        </el-col>
        <el-col :span="8" class="col5">
        </el-col>
      </el-row>
      <!-- 第二列栅格布局 -->
      <el-row :gutter="20" style="height: 100%">
        <el-col class="col1" :span="16" style="height: 100%">
          <div class="c1">
          <el-row style="height: 25%">
            <b>加盟进度</b>
            <el-steps class="step" :space="200" :active="active" finish-status="success">
              <el-step title="提交加盟申请"></el-step>
              <el-step title="等待加盟审核"></el-step>
              <el-step title="加盟成功"></el-step>
            </el-steps>
          </el-row >
          <el-row style="height: 75%" v-if="active=='0'">
            <el-form ref="form" :model="form"  class="commitForm">
              <el-form-item label="站点名称" :required="true">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
              <el-form-item label="站点所在区域" :required="true">
                <region-group
                    separator="-"
                    :town="true"
                    v-model="region"
                    @change="regionChange"
                />
              </el-form-item>
              <el-form-item label="站点详细地址" :required="true">
                <el-input v-model="form.address" placeholder="如:xx村/xx路/xx门牌号"></el-input>
              </el-form-item>
              <el-form-item label="申请人真实姓名" :required="true">
                <el-input v-model="form.realName"></el-input>
              </el-form-item>
              <el-form-item label="申请人身份证号" :required="true">
                <el-input v-model="form.id"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>
          </el-row>
            <el-row style="height: 75%" v-else-if="active=='1'">
              <div class="c1">
                <el-descriptions :column="1" >
                  <el-descriptions-item label="站点名称"></el-descriptions-item>
                  <el-descriptions-item label="站点所在区域"></el-descriptions-item>
                  <el-descriptions-item label="站点详细地址"></el-descriptions-item>
                  <el-descriptions-item label="申请人真实姓名"></el-descriptions-item>
                  <el-descriptions-item label="申请人身份证号"></el-descriptions-item>
                  <template slot="extra">
                    <el-button type="warning" size="medium" class="cancelButton" round @click.native="changePasswd" >撤销申请</el-button>
                  </template>
                </el-descriptions>
              </div>
            </el-row>
            <el-row style="height: 70%" v-else="">
              <el-col class="col1" :span="8" style="height: 100%">
                <img src="@/assets/logo.png" class="logoimg">
              </el-col>
              <el-col class="col1" :span="16" style="height: 100%">
                <b class="b2">恭喜您成为BabyQ物流的工作人员</b>
                <br>
                <b class="b1">您的加盟申请已通过审核，该账号已升级为快递点员工账号</b>
                <br>
                <b class="b1">此后，该账号即可用于登录普通用户界面用于体验正常物流运输服务</b>
                <br>
                <b class="b1">同时，该账号将有权限登录快递点员工界面为广大用户提供物流服务</b>
                <br>
                <b class="b2">让我们一起共同努力吧</b>
              </el-col>
            </el-row>
          </div>
        </el-col>

        <el-col class="col2" :span="8">
          <div class="c2">
            <b class="b1">申请历史</b>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import UserHeader from "@/components/User/UserHeader";
import { RegionGroup } from 'v-region'
export default {
  components:{UserHeader,RegionGroup},
  data() {
    return {
      active:3,
      region: {
      },
      form: {
        name: '',
        region: '',
        address: '',
        type:'',
        realName:'',
        id:'',
      }
    }
  },
  methods:{
    onSubmit()
    {
        this.active=1;
    },
    goBack(){
      this.$router.go(-1);
    },
    regionChange (data) {
      console.log(data.province.value)
      console.log(data.city.value)
      console.log(data.area.value)
      console.log(data.town.value)
    }

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
.logoimg
{
  width: 100%;
  overflow-x: hidden;
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
  top:3%;
  left: 2.5%;
  background-color: #ffffff;
  border-radius: 30px;
  width: 95%;
  height: 90%;
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
  background-color: #ffffff;
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
.commitForm
{
  position: relative;
  left: 10%;
  width: 80%;
  height: 100%;
  margin-bottom: 0px;
}
.b1
{
  font-size: 20px;
  line-height: 50px;
}
.b2
{
  color: brown;
  line-height: 50px;
}
/deep/ .el-step__head.is-success {
  color: rgb(52, 158, 250);
  border-color: rgb(52, 158, 250);
}
/deep/ .el-step__title.is-success {
  font-weight: bold;
  color: rgb(52, 158, 250);
}
/deep/ .el-step__description.is-success {
  color: rgb(52, 158, 250);
}
.el-descriptions
 {
   position: relative;
   left: 5%;
   width: 90%;
  font-size: 20px;
   /deep/ .el-descriptions__body
   {
     background-color: transparent;
   }
 }
</style>