<template>
  <el-container class="container1">
    <el-drawer
        title="重置密码"
        :visible.sync="draw"
        direction="rtl"
        :close-on-click-modal="false"
        :wrapper-closable="false">
      <el-form ref="PasswdFormRef"
               :model="PasswdForm" :rules="PasswdFromRules"class="changeForm">
        <!-- 原密码 -->
        <el-form-item label="原密码"  :required="true"  prop="originpassswd">
          <el-input v-model="PasswdForm.originpassswd" show-password size="medium"
                    @paste.native.capture.prevent="handleFalse"
                    @copy.native.capture.prevent="handleFalse"
                    @cut.native.capture.prevent="handleFalse">
          </el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" :required="true"  prop="passwd">
          <el-input v-model="PasswdForm.passwd" show-password size="medium"
                    @paste.native.capture.prevent="handleFalse"
                    @copy.native.capture.prevent="handleFalse"
                    @cut.native.capture.prevent="handleFalse">
          </el-input>
        </el-form-item>
        <!-- 确认密码 -->
        <el-form-item label="确认密码" :required="true" prop="repasswd">
          <el-input v-model="PasswdForm.repasswd" show-password size="medium"
                    @paste.native.capture.prevent="handleFalse"
                    @copy.native.capture.prevent="handleFalse"
                    @cut.native.capture.prevent="handleFalse">
          </el-input>
        </el-form-item>
        <el-form-item >
          <el-button type="primary" @click="onPasswdChange" >修改</el-button>
          <el-button @click="onExitPasswdChange">取消</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
    <!-- 顶栏 -->
    <el-drawer
        title="修改用户信息"
        :visible.sync="draw2"
        direction="rtl"
        :close-on-click-modal="false"
        :wrapper-closable="false">
      <div class="leftContainer">
        <el-form ref="ChangeFormRef"
                 :model="ChangeForm" :rules="ChangeFromRules" class="changeForm">
          <!-- 用户名 -->
          <el-form-item label="用户名" :required="false" prop="username" >
            <el-input v-model="ChangeForm.username" placeholder="用于系统登录，不可修改" size="medium" :disabled="true">
            </el-input>
          </el-form-item>
          <!-- 昵称 -->
          <el-form-item label="昵称" :required="true" prop="nickname">
            <el-input v-model="ChangeForm.nickname" placeholder="15个字符内" size="medium">
            </el-input>
          </el-form-item>
          <!-- 手机号 -->
          <el-form-item label="手机号" :required="true" prop="phone">
            <el-input v-model="ChangeForm.phone" placeholder="谨慎修改，将影响收发快递信息的获取" size="medium">
            </el-input>
          </el-form-item>
          <!-- 邮箱 -->
          <el-form-item label="邮箱" :required="true" prop="email">
            <el-input v-model="ChangeForm.email" placeholder="邮箱" size="medium">
            </el-input>
          </el-form-item>
          <!-- 个性签名 -->
          <el-form-item label="个性签名" :required="false" prop="address">
            <el-input v-model="ChangeForm.address" placeholder="个性签名，可为空" size="medium">
            </el-input>
          </el-form-item>
          <el-form-item >
            <el-button type="primary" @click="onSubmit" >修改</el-button>
            <el-button @click="onExit">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
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
                <el-button type="primary" size="medium" class="changebutton" round @click.native="changeButtonDown" >修改用户信息</el-button>
              </template>
              <template slot="extra">
                <el-button type="warning" size="medium" class="changebutton" round @click.native="changePasswd" >修改密码</el-button>
              </template>
            <el-descriptions-item label="用户名">{{userdata.username}}</el-descriptions-item>
              <el-descriptions-item label="昵称">{{userdata.nickname}}</el-descriptions-item>
            <el-descriptions-item label="手机号">{{userdata.phone}}</el-descriptions-item>
              <el-descriptions-item label="邮箱">{{userdata.email}}</el-descriptions-item>
            <el-descriptions-item label="个性签名">{{userdata.address}}</el-descriptions-item>
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
  name: "UserInfoView",
  data(){
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.PasswdForm.passwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return{
      userdata:{
      },
      ChangeForm: {
        userid:"",
        username:"",
        nickname:"",
        phone: "",
        email: "",
        address:""
      },
      ChangeFromRules: {
        // 验证用户名是否合法
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 4, max: 10, message: '长度在 4到 10个字符', trigger: 'blur'}
        ],
        nickname: [
          {required: true, message: '请输入昵称', trigger: 'blur'},
          {min: 1, max: 15, message: '1-15个字符', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {
            validator: function(rule, value, callback) {
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
            validator: function(rule, value, callback) {
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
      },
      PasswdForm: {
        originpassswd:"",
        passwd:"",
        repasswd: ""
      },
      PasswdFromRules: {
        // 验证用户名是否合法
        originpassswd:[
          {required: true, message: '请输入原密码', trigger: 'blur'},
          {pattern: /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,15}$/,
            message: '密码为数字，小写字母，大写字母，特殊符号 至少包含三种，长度为 8 - 15位'}
        ],
        passwd: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          {pattern: /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,15}$/,
            message: '密码为数字，小写字母，大写字母，特殊符号 至少包含三种，长度为 8 - 15位'}
        ],
        repasswd: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      },
      draw:false,
      draw2:false
    }
  },
  created() {
    request.get("http://localhost:9090/user/get",{params:{username:this.$store.state.user.username}})
        .then(res=>
        {
          console.log(res);
          this.userdata=res;
        })
  },
  methods:{
    goBack()
    {
      this.$router.go(-1);
    },
    changeButtonDown()
    {
      this.ChangeForm.userid=this.userdata.userid;
      this.ChangeForm.username=this.userdata.username;
      this.ChangeForm.nickname=this.userdata.nickname;
      this.ChangeForm.email=this.userdata.email;
      this.ChangeForm.phone=this.userdata.phone;
      this.ChangeForm.address=this.userdata.address;
      this.draw2=true;
    },
    changePasswd()
    {
      this.draw=true;
    },
    reset()
    {
      this.ChangeForm.username="";
      this.ChangeForm.nickname="";
      this.ChangeForm.email="";
      this.ChangeForm.phone="";
      this.ChangeForm.signature="";
    },
    onSubmit() {
      var isSuccess=false;
      this.$refs["ChangeFormRef"].validate(valid => {
        if (valid) {
          request.post("http://localhost:9090/user/changeInfo",this.ChangeForm).then(res=> {
            if (res)
            {
              this.$message({
                type: 'success',
                message: '修改成功'
              });
              //修改成功，即刻修改
              this.$router.go(0);
            }
            else
            {
              this.$message({
                type: 'warning',
                message: '修改失败'
              });
            }
          });
          this.draw2=false;
          isSuccess=true;
          this.reset();
          return;
        }
        else{
          this.DialogA = true;
          return;
        }
        this.draw2=false;
      });
      if(isSuccess==false) {
        this.$message({
          type: 'warning',
          message: '修改失败'
        });
        return;
      }
    },
    onExit()
    {
      this.draw2=false;
      this.reset();
      this.$message({
        type: 'info',
        message: '已取消修改'
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
    onPasswdChange()
    {
      console.log(this.PasswdForm.passwd);
      this.$refs["PasswdFormRef"].validate(valid => {
        if (valid) {
          var userMap =new Map();
          userMap.set("username",this.userdata.username);
          userMap.set("passwd",this.PasswdForm.originpassswd);
          let obj= Object.create(null);
          for (let[k,v] of userMap) {
            obj[k] = v;
          }
          //object转json
          JSON.stringify(obj);
          request.post("http://localhost:9090/user/checkPasswd", obj).then(res=>{
            if(res)
            {
              request.post("http://localhost:9090/user/changeInfo",{userid:this.userdata.userid,password:this.PasswdForm.passwd}).then(res=>
              {
                if(res) {
                  this.$message({
                    type: 'success',
                    message: '修改成功'
                  });
                }
                else
                {
                  this.$message({
                    type: 'warning',
                    message: '服务器错误，修改失败'
                  });
                }
              });
            }
            else
            {
              this.$message({
                type: 'warning',
                message: '原密码错误'
              });}
          });
          console.log("success submit!!");
          this.draw=false;
        }else{
          this.DialogA = true;
          console.log("error submit!!");
          this.PasswdForm.passwd="";
          this.PasswdForm.repasswd="";
        }
      });
    },
    onExitPasswdChange()
    {
      this.$message({
        type: 'info',
        message: '已取消修改'
      });
      this.PasswdForm.passwd="";
      this.PasswdForm.repasswd="";
      this.draw=false;
    }

  },
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
.changeForm
{
  position: relative;
  left: 10%;
  top: 10%;
  width: 80%;
}
.leftContainer {
  position: relative;
  top: 2.5%;
  left: 2.5%;
  background-color: #ffffff;
  border-radius: 30px;
  width: 95%;
  height: 95%;
}

  /deep/.el-descriptions-item__cell
  {
    padding: 20px;
    font-size: 20px;
  }
</style>