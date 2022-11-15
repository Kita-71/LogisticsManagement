<template>
  <div class="login_view_container">
    <div class="login_container">
      <div class="picture_box">
        <img src ="../assets/truck.jpg" alt="" style="height: 100%">
      </div>
      <div class="picture_box">
        <b>MAKE YOUR LIFE</b>
        <br>
        <b>CONVENIENT</b>
      </div>
    <div class="login_box">
      <el-tabs v-model="activeName" @tab-click="handleClick" >
        <el-tab-pane label="登录" name="first">
          <b >欢迎登录</b>
          <br>
          <b>BabyQ物流平台</b>
          <!-- 登陆表单区域 -->
          <el-form ref="loginFormRef"  :model="LoginForm" :rules="LoginfromRules"   class="login_form">
            <!-- 用戶名 -->
            <el-form-item prop="username">
              <el-input v-model="LoginForm.username" placeholder="Email/Phone/ID" size="medium">
              </el-input>
            </el-form-item>
            <!-- 密碼 -->
            <el-form-item prop="password" >
              <el-input v-model="LoginForm.password" placeholder="请输入密码" show-password size="medium">
              </el-input>
            </el-form-item>
            <!-- 按鈕 -->
            <el-form-item  class="btns">
              <el-button type="primary" round>登录</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="second">
          <b >欢迎注册</b>
          <br>
          <b>BabyQ物流平台</b>
          <!-- 登陆表单区域 -->
          <el-form ref="loginFormRef"  :model="RegisterForm" :rules="RegisterfromRules"   class="login_form">
            <!-- 手机号 -->
            <el-form-item prop="username">
              <el-input v-model="RegisterForm.phone" placeholder="Phone Number" size="medium">
              </el-input>
            </el-form-item>
            <!-- 邮箱 -->
            <el-form-item prop="username">
              <el-input v-model="RegisterForm.email" placeholder="Email" size="medium">
              </el-input>
            </el-form-item>
            <!-- 密碼 -->
            <el-form-item prop="password" >
              <el-input v-model="RegisterForm.password" placeholder="password" show-password size="medium">
              </el-input>
            </el-form-item>
            <!-- 按鈕 -->
            <el-form-item  class="btns">
              <el-button type="primary" round>注册</el-button>
            </el-form-item>
          </el-form>

        </el-tab-pane>
      </el-tabs>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      activeName:'first',//默认显示登录页面
      LoginForm:{
        username: "",
        password:""
      },
      LoginfromRules:{
        // 验证用户名是否合法
        username: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        // 验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
        ]
      },
      RegisterForm:{
        phone: "",
        email:"",
        password:""
      },
      RegisterfromRules:{
        // 验证用户名是否合法
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { min: 11, max: 11, message: '请输入长度为11位的手机号', trigger: 'blur' }
        ],
        // 验证密码是否合法
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
        ],
        password: [
      { required: true, message: '请输入登录密码', trigger: 'blur' },
      { min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur' }
    ]
      }
    }
  },
  methods:{
    resetLoginForm(){
      this.$refs.loginFormRef.resetFields();
    },
    handleClick(){}
  }
}
</script>
<style lang="less" scoped>
.login_view_container{
  background-image: linear-gradient(120deg, #a6c0fe 0%, #f68084 100%);
  height: 100%;
}
.login_container{
  height:900px;
  width:1000px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}


.login_box{
  width: 420px;
  height: 650px;
  background-color: #fdfdfd;
  box-shadow:
      0.3em 0.3em 1em rgba(0,0,0,0.5);
  position: absolute;
  left: 80%;
  top: 50%;
  transform: translate(-50%,-50%);
  font-size: 40px;
  font-family: 'Times New Roman', "Times New Roman";
  line-height:70px;
  letter-spacing: 1px;
  color: #000000;
  .el-tab-pane
  {
    height: 600px;
    width: 100%;
  }
  .el-tabs
  {
      margin-left: 20px;
  }
  b{
    position: relative;
    left:8%;
    top: 5%;
  }
  .el-form
  {
    position: relative;
    left:5%;
    top: 15%;
    .el-form-item
    {
      height:40px;
      width: 300px;
    }
    .el-button
    {
      margin-right:150px;
      height: 40px;
      width: 150px;
    }
  }
}
.picture_box{
  width: 580px;
  height: 580px;
  background-image: linear-gradient(120deg, rgba(137,247,254, 0.3) 0%, rgba(102,166,255, 0.3) 60%);
  position: absolute;
  box-shadow:
      0.3em 0.3em 1em rgba(0,0,0,0.3);
  left: 30%;
  top: 50%;
  transform: translate(-50%,-50%);
  font-size: 60px;
  font-family: 'Helvetica Neue', "Helvetica Neue";
  line-height:120px;
  letter-spacing: 1px;
  color: #f5f7fa;
  text-shadow: 3px 3px 1px black;
  b{
    margin-left: 20px
  }
}
.login_form{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  // 元素指定宽度和高度包括了 padding 和 border
  box-sizing: border-box;

}
.btns{
  display: flex;
  justify-content: center;
}
</style>

