<template>

  <el-container class="container1">
    <!-- 顶栏 -->
    <el-header height="60px">
      <UserHeader></UserHeader>
    </el-header>
    <el-main>

      <el-row >
        <el-col :span="24" class="infocol2">
          <el-page-header @back="goBack" content="个人信息">
          </el-page-header>
        </el-col>
      </el-row>

      <el-row >
        <el-col :span="24" class="infocol">
          <div class="c1">
            <el-descriptions :column="1" >
              <template slot="extra">
                <el-button type="primary" size="medium" class="changebutton" round >修改</el-button>
              </template>
            <el-descriptions-item label="用户名">Kita</el-descriptions-item>
            <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
              <el-descriptions-item label="邮箱">wghau@outlook.com</el-descriptions-item>
            <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
              <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
            <el-descriptions-item label="个性签名"></el-descriptions-item>
          </el-descriptions>
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
  name: "UserInfoView"
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

.infocol
{
  height: 100%;
}
.infocol2
{
  height: 24px;
}
.changebutton
{
  background-color: #828cde;
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
  /deep/.el-descriptions-item__cell
  {
    padding: 20px;
    font-size: 20px;
  }
</style>