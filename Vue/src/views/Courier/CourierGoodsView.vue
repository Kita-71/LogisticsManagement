<template>
  <el-container class="containerAll">
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
          <el-form-item label="物品名" :required="true" prop="username">
            <el-input v-model="ChangeForm.username" placeholder="用户名" size="medium">
            </el-input>
          </el-form-item>
          <!-- 手机号 -->
          <el-form-item label="目的地" :required="true" prop="phone">
            <el-input v-model="ChangeForm.phone" placeholder="手机号" size="medium">
            </el-input>
          </el-form-item>
          <!-- 邮箱 -->
          <el-form-item label="现在位置" :required="true" prop="email">
            <el-input v-model="ChangeForm.email" placeholder="邮箱" size="medium">
            </el-input>
          </el-form-item>
          <!-- 权限-->
          <el-form-item label="用户角色" :required="true" class="btnItem" >
            <el-select v-model="ChangeForm.permission" placeholder="请选择用户角色">
              <el-option label="普通用户" value="普通用户"></el-option>
              <el-option label="快递点员工" value="快递点员工"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item >
            <el-button type="primary" @click="onSubmit" >修改</el-button>
            <el-button @click="onExit">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
    <el-dialog
        title="提示"
        :visible.sync="DialogA"
        width="30%">
      <span>按照提示进行修改</span>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="DialogA = false">确 定</el-button>
  </span>
    </el-dialog>
    <el-header >
      <CourierHeader></CourierHeader>
    </el-header>
    <el-container class="containerBottom">
      <el-aside class="aside" width="260px" >
        <CourierAside></CourierAside>
      </el-aside>
      <el-main>
        <el-col :span="24" style="height: 100%">
          <div class="rightContainer">
            <el-row>
              <el-col :span="6">
                <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
                  <el-breadcrumb-item :to="{ path: '/CourierHome' }">主页</el-breadcrumb-item>
                  <el-breadcrumb-item>快递点订单管理</el-breadcrumb-item>
                </el-breadcrumb></el-col>
              <el-col :span="6">
                <el-select v-model="value" placeholder="请选择" class="searchSelect">
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="6">
                <el-input
                    placeholder= "请输入与选择框对应的查询内容"
                    v-model="search_input"
                    class="search">
                  <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                </el-input>
              </el-col>
            </el-row>
            <el-row>

              <el-table
                  :data="tableData"
                  class="table"
                  border
                  height="85%"
              >
                <el-table-column
                    prop="userid"
                    label="id">
                </el-table-column>
                <el-table-column
                    prop="username"
                    label="物品名">
                </el-table-column>
                <el-table-column
                    prop="phone"
                    label="目的地">
                </el-table-column>
                <el-table-column
                    prop="email"
                    label="邮箱">
                </el-table-column>
                <el-table-column
                    prop="permission"
                    label="角色">
                </el-table-column>
                <el-table-column
                    label="操作">
                  <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">发货
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-row>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage4"
                :page-sizes="[9, 18, 27,36]"
                :page-size="9"
                layout="total, sizes, prev, pager, next, jumper"
                :total="400"
                class="pagination">
            </el-pagination>
          </div>
        </el-col>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
import CourierAside from "@/components/Courier/CourierAside";
import CourierHeader from "@/components/Courier/CourierHeader";
export default {
  name: "CourierOrderView",
  components:{CourierAside,CourierHeader},
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.PasswdForm.passwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      tableData: [{
        userid: '1',
        username: 'Kita',
        phone:'13126078008',
        email:'842935300@qq.com',
        permission:"普通用户"
      }],
      ChangeForm: {
        username:"",
        phone: "",
        email: "",
        permission:""
      },
      ChangeFromRules: {
        // 验证用户名是否合法
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 4, max: 8, message: '长度在 4到 8个字符', trigger: 'blur'}
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
        password: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          { pattern: /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,15}$/,
            message: '密码为数字，小写字母，大写字母，特殊符号 至少包含三种，长度为 8 - 15位'}
        ]
      },
      PasswdForm: {
        passwd:"",
        repasswd: ""
      },
      PasswdFromRules: {
        // 验证用户名是否合法
        passwd: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          {pattern: /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,15}$/,
            message: '密码为数字，小写字母，大写字母，特殊符号 至少包含三种，长度为 8 - 15位'}
        ],
        repasswd: [
          { required: true, validator: validatePass2, trigger: 'blur' }
        ]
      },
      search_input:"",
      options: [{
        value: '用户名',
        label: '用户名'
      }, {
        value: '手机号',
        label: '手机号'
      }, {
        value: '邮箱',
        label: '邮箱'
      }],
      value:"用户名",
      draw:false,
      draw2:false,
      DialogA:false,
    }
  },
  methods: {
    onSubmit() {
      this.$refs["ChangeFormRef"].validate(valid => {
        if (valid) {
          this.draw2=false;
          this.ChangeForm.username="";
          this.ChangeForm.password="";
          this.ChangeForm.email="";
          this.ChangeForm.permission="";
          this.ChangeForm.phone="";
          this.$message({
            type: 'success',
            message: '修改成功'
          });
        }else{
          this.DialogA = true;
        }
      });
    },
    onExit()
    {
      this.draw2=false;
      this.ChangeForm.username="";
      this.ChangeForm.password="";
      this.ChangeForm.email="";
      this.ChangeForm.permission="";
      this.ChangeForm.phone="";
      this.$message({
        type: 'info',
        message: '已取消修改'
      });
    },
    onPasswdChange()
    {
      this.$refs["PasswdFormRef"].validate(valid => {
        if (valid) {
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          console.log("success submit!!");
        }else{
          this.DialogA = true;
          console.log("error submit!!");
        }
      });
    },
    onExitPasswdChange()
    {
      this.$message({
        type: 'info',
        message: '已取消修改'
      });
      this.draw=false;
    },
    handleEdit(index, row) {
      this.draw2=true;
      this.ChangeForm.username=row.username;
      this.ChangeForm.phone=row.phone;
      this.ChangeForm.email=row.email;
      this.ChangeForm.permission=row.permission;
    },
    handleDelete(index,row){
      this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
        cancelButtonText: '取消',
        confirmButtonText: '确定',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    search()
    {
      this.$message({
        type: 'success',
        message: '搜索已提交'
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
  }
}
</script>

<style lang="less" scoped>
.containerAll
{
  height: 100%;
}
.containerBottom
{
  height: 100%;
}
.el-header
{
  text-align: right;
  font-size: 12px;
  padding:0;
}
.el-aside
{
  height: 100%;
}
.pagination
{
  position: absolute;
  top: 95%;
  left:15%;


}
.el-main
{
  height: 100%;
  background-color: #fafafa;
}
</style>