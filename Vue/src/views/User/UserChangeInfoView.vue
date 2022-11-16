<template>

  <el-container class="container1">
    <!-- 顶栏 -->
    <el-header height="60px">
      <UserHeader></UserHeader>
    </el-header>
    <el-main>

      <el-row >
        <el-col :span="24" class="infocol2">
          <el-page-header @back="goBack" content="个人信息修改">
          </el-page-header>
        </el-col>
      </el-row>

      <el-row >
        <el-col :span="24" class="infocol">
          <div class="c1">
            <el-button class="okbutton" type="primary" round>确认修改</el-button>
            <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
              <el-form-item label="名称">
                <el-input v-model="formLabelAlign.name"></el-input>
              </el-form-item>
              <el-form-item label="活动区域">
                <el-input v-model="formLabelAlign.region"></el-input>
              </el-form-item>
              <el-form-item label="活动形式">
                <el-input v-model="formLabelAlign.type"></el-input>
              </el-form-item>
            </el-form>
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
  name: "UserInfoView",
  data(){
    return {
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      }
    };
  }
}
</script>

<style lang="less" scoped>
.el-header {
  padding: 0px;
}
.el-main {
  background-image: linear-gradient(0deg, 	rgb(245,247,250,0.1) 0%, 	rgba(195,207,226,0.1) 70%),linear-gradient(-225deg, #5271C4 0%, #B19FFF 48%, #ECA1FE 100%);
  height: 100%;
}

.el-container{
  height: 100%;
}
.el-form.el-form--label-right
{
  position: relative;
  top:10%;
  left: 10%;
  width: 80%;
}
.infocol
{
  height: 100%;
}
.infocol2
{
  height: 24px;
}
.okbutton
{
  background-color: #828cde;
  position: relative;
  left: 85%;
  width: 100px;
  top: 5%;
}
.c1
{
  position: relative;
  top:50px;
  left: 10%;
  background-image: linear-gradient(-225deg,rgba(255,255,255,0.5) 0%,rgba(255,255,255,0.5) 100%);
  border-radius: 30px;
  width: 80%;
  height: 900px;
  b
  {
    font-size: 18px;
    position: relative;
    top: 30px;
    left: 40px;
  }
}
</style>