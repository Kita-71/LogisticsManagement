<template>
  <div class="login_view_container">
    <el-dialog
        title="提示"
        :visible.sync="DialogA"
        width="30%">
      <span>密码错误</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="DialogA = false">确 定</el-button>
  </span>
    </el-dialog>
    <el-dialog
        title="提示"
        :visible.sync="DialogB"
        width="30%">
      <span>该用户不存在</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="DialogB = false">确 定</el-button>
  </span>
    </el-dialog>
    <el-dialog
        title="提示"
        :visible.sync="DialogC"
        width="30%">
      <span>请按指定操作进行注册</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="DialogC = false">确 定</el-button>
  </span>
    </el-dialog>
    <el-dialog
        title="提示"
        :visible.sync="DialogD"
        width="30%">
      <span>请按指定操作进行登录</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="DialogD = false">确 定</el-button>
  </span>
    </el-dialog>
    <div class="login_container">
      <div class="picture_box">
        <img src="../assets/truck.jpg" alt="" style="height: 100%">
      </div>
      <div class="picture_box">
        <b>MAKE YOUR LIFE</b>
        <br>
        <b>CONVENIENT</b>
      </div>
      <div class="login_box">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="登录" name="first">
            <b class="title">欢迎登录</b>
            <br>
            <b class="title">BabyQ物流平台</b>
            <!-- 登陆表单区域 -->
            <el-form ref="loginFormRef" :model="LoginForm" :rules="LoginFromRules" class="login_form">
              <!-- 用戶名 -->
              <el-form-item prop="username" label="用户名" :required="true">
                <el-input v-model="LoginForm.username" placeholder="4-10字符长度" size="medium">
                </el-input>
              </el-form-item>
              <!-- 密碼 -->
              <el-form-item prop="password" label="密码" :required="true">
                <el-input v-model="LoginForm.password" placeholder="8-15位密码" show-password size="medium">
                </el-input>
              </el-form-item>
              <!-- 按鈕 -->
              <el-form-item class="btns">
                <el-button type="primary" @click="signIn" round>登录</el-button>
              </el-form-item>
            </el-form>
            <b class="tip">忘记密码请致电管理员:xxxxxxxx</b>
          </el-tab-pane>
          <el-tab-pane label="注册" name="second">
            <b class="title">欢迎注册</b>
            <br>
            <b class="title">BabyQ物流平台</b>
            <!-- 登陆表单区域 -->
            <el-form ref="registerFormRef" :model="RegisterForm" :rules="RegisterFromRules" class="login_form">
              <!-- 用户名 -->
              <el-form-item prop="username" label="用户名" :required="true">
                <el-input v-model="RegisterForm.username" placeholder="4-10字符长度，不可修改" size="medium">
                </el-input>
              </el-form-item>
              <!-- 手机号 -->
              <el-form-item prop="phone" label="手机号" :required="true">
                <el-input v-model="RegisterForm.phone" placeholder="11位手机号" size="medium">
                </el-input>
              </el-form-item>
              <!-- 邮箱 -->
              <el-form-item prop="email" label="邮箱地址" :required="true">
                <el-input v-model="RegisterForm.email" placeholder="邮箱" size="medium">
                </el-input>
              </el-form-item>
              <!-- 密碼 -->
              <el-form-item prop="password" label="密码" :required="true">
                <el-input v-model="RegisterForm.password" placeholder="长度为 8 - 15位" show-password size="medium"
                          @paste.native.capture.prevent="handleFalse"
                          @copy.native.capture.prevent="handleFalse"
                          @cut.native.capture.prevent="handleFalse">
                </el-input>
              </el-form-item>
              <!-- 确认密碼 -->
              <el-form-item prop="repassword" label="确认密码" :required="true">
                <el-input v-model="RegisterForm.repassword" placeholder="长度为 8 - 15位" show-password size="medium"
                          @paste.native.capture.prevent="handleFalse"
                          @copy.native.capture.prevent="handleFalse"
                          @cut.native.capture.prevent="handleFalse">
                </el-input>
              </el-form-item>
              <!-- 按鈕 -->
              <el-form-item class="btns">
                <el-button type="primary" round @click.native="signUp">注册</el-button>
              </el-form-item>
            </el-form>

          </el-tab-pane>
        </el-tabs>

      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.RegisterForm.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      activeName: 'first',//默认显示登录页面
      LoginForm: {
        username: "",
        password: ""
      },
      LoginFromRules: {
        // 验证用户名是否合法
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 4, max: 10, message: '长度在 4到 10个字符', trigger: 'blur'}
        ],
        // 验证密码是否合法
        password: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          {
            pattern: /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,15}$/,
            message: '至少包含数字、小写字母、大写字母、特殊符号中的三种，长度为 8 - 15位'
          }
        ]
      },
      RegisterForm: {
        username: "",
        phone: "",
        email: "",
        password: "",
        repassword: "",
        nickname:"BabyQ新用户",
      },
      RegisterFromRules: {
        // 验证用户名是否合法
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 4, max: 10, message: '长度在 4到 10个字符' +
                '' +
                '', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {
            validator: function (rule, value, callback) {
              if (/^1[34578]\d{9}$/.test(value) == false) {
                callback(new Error("手机号格式错误"));
              } else {
                callback();
              }
            },
            trigger: "blur"
          }
        ],
        email: [
          {
            required: true,
            message: "请输入邮箱",
            trigger: "blur"
          },
          {
            validator: function (rule, value, callback) {
              if (
                  /^\w{1,64}@[a-z0-9\-]{1,256}(\.[a-z]{2,6}){1,2}$/i.test(
                      value
                  ) == false
              ) {
                callback(new Error("邮箱格式错误"));
              } else {
                callback();
              }
            },
            trigger: "blur"
          }
        ],
        password: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          {
            pattern: /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,15}$/,
            message: '密码为数字，小写字母，大写字母，特殊符号 至少包含三种，长度为 8 - 15位'
          }
        ],
        repassword: [
          {required: true, validator: validatePass2, trigger: 'blur'}
        ]
      },
      DialogA: false,
      DialogB: false,
      DialogC: false,
      DialogD: false
    }
  },
  methods: {
    //signIn接口，
    //传入参数username和passwd
    //返回内容为
    // 1.state{0为用户名不存在，1为密码错误，2为用户名及密码正确}
    // 2.permisson{0为普通用户，1为快递点员工用户，2为管理员用户，若state不为2，返回值无所谓}
    signIn() {
      console.log(require);
      this.$refs["loginFormRef"].validate(valid => {
        if (valid) {
          var require = this.$route.query.access;
          console.log(require);
          var userMap = new Map();
          userMap.set("username", this.LoginForm.username);
          userMap.set("passwd", this.LoginForm.password);
          userMap.set("require", require);
          let obj = Object.create(null);
          for (let [k, v] of userMap) {
            obj[k] = v;
          }
          //object转json
          JSON.stringify(obj);
          request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
            if (res["user"] === false) {
              this.$message({
                type: 'warning',
                message: '用户不存在'
              });
            } else if (res["password"] === false) {
              this.$message({
                type: 'warning',
                message: '密码错误'
              });
            } else if (res["permission"] === false) {
              if (require === "" || require === undefined) {
                request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                  if (res["user"] === false) {
                    this.$message({
                      type: 'warning',
                      message: '用户不存在'
                    });
                  } else if (res["password"] === false) {
                    this.$message({
                      type: 'warning',
                      message: '密码错误'
                    });
                  } else if (res["permission"] === false) {
                    require = "commonUser";
                    userMap.set("require", require);
                    for (let [k, v] of userMap) {
                      obj[k] = v;
                    }
                    console.log(require);
                    request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                      if (res["permission"] === true) {
                        this.$message({
                          type: 'success',
                          message: '登录成功'
                        });
                        this.$store.commit('user_login', this.LoginForm);
                        this.$router.replace('/UserHome');
                      } else {
                        require = "deliveryStaff";
                        console.log(require);
                        userMap.set("require", require);
                        for (let [k, v] of userMap) {
                          obj[k] = v;
                        }
                        request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                          if (res["permission"] === true) {
                            this.$message({
                              type: 'success',
                              message: '登录成功'
                            });
                            this.$store.commit('courier_login', this.LoginForm);
                            this.$router.replace('/CourierHome');
                          } else {
                            require = "admin";
                            console.log(require);
                            userMap.set("require", require);
                            for (let [k, v] of userMap) {
                              obj[k] = v;
                            }
                            request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                              if (res["permission"] === true) {
                                this.$message({
                                  type: 'success',
                                  message: '登录成功'
                                });
                                this.$store.commit('admin_login', this.LoginForm);
                                this.$router.replace('/ManagerHome');
                              } else {
                                this.$message({
                                  type: 'error',
                                  message: '用户异常，请联系管理员'
                                });
                              }
                            });
                          }
                        });
                      }
                    });
                  }
                })
              }
              else
              {
                this.$message({
                  type: 'success',
                  message: '无权限登入先前所希望登录的页面,已自动跳转到用户拥有权限的主页'
                });
                request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                  if (res["user"] === false) {
                    this.$message({
                      type: 'warning',
                      message: '用户不存在'
                    });
                  } else if (res["password"] === false) {
                    this.$message({
                      type: 'warning',
                      message: '密码错误'
                    });
                  } else if (res["permission"] === false) {
                    require = "commonUser";
                    userMap.set("require", require);
                    for (let [k, v] of userMap) {
                      obj[k] = v;
                    }
                    console.log(require);
                    request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                      if (res["permission"] === true) {
                        this.$store.commit('user_login', this.LoginForm);
                        this.$router.replace('/UserHome');
                      } else {
                        require = "deliveryStaff";
                        console.log(require);
                        userMap.set("require", require);
                        for (let [k, v] of userMap) {
                          obj[k] = v;
                        }
                        request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                          if (res["permission"] === true) {
                            this.$store.commit('courier_login', this.LoginForm);
                            this.$router.replace('/CourierHome');
                          } else {
                            require = "admin";
                            console.log(require);
                            userMap.set("require", require);
                            for (let [k, v] of userMap) {
                              obj[k] = v;
                            }
                            request.post("http://localhost:9090/user/checkAccess", obj).then(res => {
                              if (res["permission"] === true) {
                                this.$store.commit('admin_login', this.LoginForm);
                                this.$router.replace('/ManagerHome');
                              } else {
                                this.$message({
                                  type: 'error',
                                  message: '用户异常，请联系管理员'
                                });
                              }
                            });
                          }
                        });
                      }
                    });
                  }
                })
              }
            } else {
              const path = this.$route.query.redirect;
              if (require === "commonUser") {
                this.$message({
                  type: 'success',
                  message: '登录成功'
                });
                this.$store.commit('user_login', this.LoginForm);
                this.$router.replace({path: path === '/Sign' || path === undefined ? '/UserHome' : path});
              } else if (require === "deliveryStaff") {
                this.$message({
                  type: 'success',
                  message: '登录成功'
                });
                this.$store.commit('courier_login', this.LoginForm);
                this.$router.replace({path: path === '/Sign' || path === undefined ? '/CourierHome' : path});
              } else if (require === "admin") {
                this.$message({
                  type: 'success',
                  message: '登录成功'
                });
                this.$store.commit('admin_login', this.LoginForm);
                this.$router.replace({path: path === '/Sign' || path === undefined ? '/ManagerHome' : path});
              }
            }
          })
        } else {
          this.DialogD = true;
          console.log("error submit!!");
        }
      });
    },

    signUp() {

      this.$refs["registerFormRef"].validate(valid => {
        if (valid) {
          request.post("http://localhost:9090/user/signUp",this.RegisterForm).then(res=>
              {
                 if(res["user_exist"]==true)
                 {
                   this.$message({
                     type: 'waring',
                     message: '该用户已存在'
                   });
                 }
                 else
                 {
                      if(res["signup_success"]==true)
                      {
                        this.$message({
                          type: 'success',
                          message: '注册成功'
                        });
                        this.LoginForm.username = this.RegisterForm.username;
                        this.LoginForm.password = this.RegisterForm.password;
                        this.activeName = 'first';
                      }
                 }
              }
          );
        } else {
          this.DialogC = true;
          console.log("error submit!!");
        }
      });
    },
    handleFalse() {
      //终止
      this.$message({
        type: 'info',
        message: '密码框禁止复制粘贴'
      });
      return false;
    },
    handleClick() {
    }
  }
}
</script>
<style lang="less" scoped>
.login_view_container {
  background-image: linear-gradient(120deg, #a6c0fe 0%, #f68084 100%);
  height: 100%;
}

.login_container {
  height: 900px;
  width: 1000px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}


.login_box {
  width: 420px;
  height: 650px;
  background-color: #fdfdfd;
  box-shadow: 0.3em 0.3em 1em rgba(0, 0, 0, 0.5);
  position: absolute;
  left: 80%;
  top: 50%;
  transform: translate(-50%, -50%);
  font-size: 40px;
  font-family: 'Times New Roman', "Times New Roman";
  line-height: 50px;
  letter-spacing: 1px;
  color: #000000;

  .el-tab-pane {
    height: 600px;
    width: 100%;
  }

  .el-tabs {
    margin-left: 20px;
  }

  .title {
    position: relative;
    left: 8%;
    top: 5%;
  }

  .el-form {
    position: relative;
    left: 5%;
    top: 15%;
    text-align: right;

    .el-form-item {
      height: 40px;
      width: 300px;
    }

    .el-button {
      margin-right: 150px;
      height: 40px;
      width: 150px;
    }

    .el-input {
      width: 200px;
    }
  }
}

.picture_box {
  width: 580px;
  height: 580px;
  background-image: linear-gradient(120deg, rgba(137, 247, 254, 0.3) 0%, rgba(102, 166, 255, 0.3) 60%);
  position: absolute;
  box-shadow: 0.3em 0.3em 1em rgba(0, 0, 0, 0.3);
  left: 30%;
  top: 50%;
  transform: translate(-50%, -50%);
  font-size: 60px;
  font-family: 'Helvetica Neue', "Helvetica Neue";
  line-height: 120px;
  letter-spacing: 1px;
  color: #f5f7fa;
  text-shadow: 3px 3px 1px black;

  b {
    margin-left: 20px
  }
}

.tip {
  font-size: 15px;
  color: darkgray;
  position: absolute;
  top: 80%;
  left: 20%;
}

.login_form {
  position: absolute;
  width: 100%;
  padding: 0 20px;
  // 元素指定宽度和高度包括了 padding 和 border
  box-sizing: border-box;

}

.btns {
  position: relative;
  top: 10px;
  display: flex;
  justify-content: center;
}

</style>

