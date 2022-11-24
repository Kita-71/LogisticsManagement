<template>
  <el-container class="containerAll">
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
      <ManagerHeader></ManagerHeader>
    </el-header>

    <el-container class="containerBottom">
      <el-aside class="aside" width="260px" >
        <ManagerAside></ManagerAside>
      </el-aside>
      <el-main>
        <el-row :gutter="20" >
          <el-col :span="16" >
            <div class="rightContainer">
              <el-row>
                <el-col :span="6">
                  <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
                    <el-breadcrumb-item :to="{ path: '/ManagerInfoHome' }">信息管理</el-breadcrumb-item>
                    <el-breadcrumb-item>快递点管理</el-breadcrumb-item>
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

              <el-table
                  :data="tableData"
                  class="table"
                  border
                  height="85%"
              >
                <el-table-column
                    prop="userid"
                    label="id"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="username"
                    label="用户名"
                    width="130">
                </el-table-column>
                <el-table-column
                    prop="phone"
                    label="手机号"
                    width="150">
                </el-table-column>
                <el-table-column
                    prop="email"
                    label="邮箱"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="permission"
                    label="角色"
                    width="100">
                </el-table-column>
                <el-table-column
                    label="操作">
                  <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">修改
                    </el-button>
                    <el-button
                        size="mini"
                        @click="handleDelete(scope.$index, scope.row)"
                        type="danger">删除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
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
          <el-col :span="8">
            <div class="leftContainer"
                 v-loading="loading"
                 element-loading-text="请在左方选择修改项">
              <b class="ChangeTitle">修改用户信息</b>
              <el-form ref="ChangeFormRef"
                       :model="ChangeForm" :rules="ChangeFromRules" class="changeForm">
                <!-- 用户名 -->
                <el-form-item label="用户名" :required="true" prop="username">
                  <el-input v-model="ChangeForm.username" placeholder="用户名" size="medium">
                  </el-input>
                </el-form-item>
                <!-- 手机号 -->
                <el-form-item label="手机号" :required="true" prop="phone">
                  <el-input v-model="ChangeForm.phone" placeholder="手机号" size="medium">
                  </el-input>
                </el-form-item>
                <!-- 邮箱 -->
                <el-form-item label="邮箱" :required="true" prop="email">
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
          </el-col>
        </el-row>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import ManagerAside from "@/components/Manager/ManagerAside";
import ManagerHeader from "@/components/Manager/ManagerHeader";
export default {
  name: "ManagerHome",
  components:{ManagerAside,ManagerHeader},
  data() {
    return {
      tableData: [{
        userid: '1',
        username: 'Kita',
        phone: '13126078008',
        email: '842935300@qq.com',
        permission: "普通用户"
      }],
      ChangeForm: {
        username: "",
        phone: "",
        email: "",
        permission: ""
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
      },
      loading: true,
      DialogA: false,
      search_input: "",
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
      value: "用户名",//当前选择项
      //分页相关变量
    }
  },
  methods: {
    onSubmit() {
      this.$refs["ChangeFormRef"].validate(valid => {
        if (valid) {
          console.log("success submit!!");
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          this.loading=true;
          this.ChangeForm.username="";
          this.ChangeForm.password="";
          this.ChangeForm.email="";
          this.ChangeForm.permission="";
          this.ChangeForm.phone="";
        }else{
          this.DialogA = true;
          console.log("error submit!!");
        }
      });
    },
    onExit()
    {
      this.$message({
        type: 'info',
        message: '已取消修改'
      });
      this.loading=true;
      this.ChangeForm.username="";
      this.ChangeForm.password="";
      this.ChangeForm.email="";
      this.ChangeForm.permission="";
      this.ChangeForm.phone="";
    },
    handleEdit(index, row) {
      this.loading=false;
      this.ChangeForm.username=row.username;
      this.ChangeForm.phone=row.phone;
      this.ChangeForm.email=row.email;
      this.ChangeForm.permission=row.permission;
    },
    handleDelete(index,row){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
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
    }

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
.el-main
{
  height: 100%;
  background-color: #fafafa;
}
.el-row
{
  height: 100%;
}
.el-col
{
  height: 100%;
}
.rightContainer {
  position: relative;
  top: 2.5%;
  left: 2.5%;
  background-color: #ffffff;
  border-radius: 30px;
  width: 95%;
  height: 95%;
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
.table{
  position: absolute;
  top: 5%;
  left: 2.5%;
  width: 95%;
  margin-top: 2.5%;
}
.pagination
{
  position: absolute;
  left: 2.5%;
  top: 95%;
}
.changeForm
{
  position: relative;
  left: 10%;
  top: 10%;
  width: 80%;
}
.btnItem
{
  margin-top: 30px;
}
.bread
{
  font-size: 15px;
  position: relative;
  top: 3.5%;
  left: 10%;
}
.searchSelect
{
  position: relative;
  top: 2%;
  width: 130px;
}
.search
{
  position: relative;
  top: 2%;
  transform: translate(-50%,0);
}
.ChangeTitle
{
  font-size: 30px;
  position: relative;
  top: 5%;
  left: 30%;
}
</style>