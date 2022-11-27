<template>
  <el-container class="containerAll">
    <el-drawer
      title="修改公告"
      :visible.sync="draw2"
      direction="rtl"
      :close-on-click-modal="false"
      :wrapper-closable="false">
    <el-form ref="InformationFormRef"
             :model="InformationForm" :rules="InformationFromRules" class="changeForm">
      <!-- 密码 -->
      <el-form-item label="标题" :required="true" prop="title">
        <el-input v-model="InformationForm.title"  size="medium">
        </el-input>
      </el-form-item>
      <!-- 确认密码 -->
      <el-form-item label="描述" :required="true" prop="description">
        <el-input  type="textarea"  :autosize="{ minRows: 2, maxRows: 4}" v-model="InformationForm.description" size="medium" >
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleEdit">修改</el-button>
        <el-button @click="onExitEdit">取消</el-button>
      </el-form-item>
    </el-form>
  </el-drawer>
    <el-drawer
        title="新增公告"
        :visible.sync="draw"
        direction="rtl"
        :close-on-click-modal="false"
        :wrapper-closable="false">
      <el-form ref="InformationFormRef"
               :model="InformationForm" :rules="InformationFromRules" class="changeForm">
        <!-- 密码 -->
        <el-form-item label="标题" :required="true" prop="title">
          <el-input v-model="InformationForm.title"  size="medium">
          </el-input>
        </el-form-item>
        <!-- 确认密码 -->
        <el-form-item label="描述" :required="true" prop="description">
          <el-input  type="textarea"  :autosize="{ minRows: 2, maxRows: 4}" v-model="InformationForm.description" size="medium" >
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="newInfo">添加</el-button>
          <el-button @click="onExit">取消</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
    <el-header >
      <ManagerHeader></ManagerHeader>
    </el-header>
    <el-container class="containerBottom">
      <el-aside class="aside" width="260px" >
        <ManagerAside></ManagerAside>
      </el-aside>
      <el-main>
        <el-col :span="24" style="height: 100%">
          <div class="rightContainer">
            <el-row :gutter="20"   style="top: 5%;height: 10%">
                <el-col :span="6" >
                  <div>
                    <el-statistic group-separator=","  :value="value2" title="平台用户量"></el-statistic>
                  </div>
                </el-col>
                <el-col :span="6" >
                  <div>
                    <el-statistic  title="全国站点数" :value="value3">
                    </el-statistic>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div>
                    <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value1" title="物流订单总量">
                      <template slot="prefix">
                        <i class="el-icon-s-flag" style="color: red"></i>
                      </template>
                      <template slot="suffix">
                        <i class="el-icon-s-flag" style="color: blue"></i>
                      </template>
                    </el-statistic>
                  </div>

                </el-col>
                <el-col :span="6">
                  <div>
                    <el-statistic :value="like ? 521 : 520" title="运输中的订单">
                    </el-statistic>
                  </div>
                </el-col>
              </el-row>
            <el-row  style="height: 5%">
              <el-col :span="12" style="height: 100%">
              </el-col>
              <el-col :span="12" style="height: 100%">
                <b style="position: relative;left: 20px;top:150%">公告栏</b>
                <el-button style="position: relative;left: 40px;top:150%" type="primary" class="newButton" @click="this.newInfoButton">新增公告</el-button>
              </el-col>
            </el-row>
            <el-row style="top: 5%;height:85%">
              <el-col :span="12" style="height: 100%">
                <img src="@/assets/logo.png" style="width: 100%">
              </el-col>
                <el-col :span="12" style="height: 100%">
                  <el-table
                      :data="this.tableData"
                      class="table"
                      border
                      height="85%"
                  >
                    <el-table-column
                        prop="title"
                        label="标题"
                        width="80">
                    </el-table-column>
                    <el-table-column
                        prop="description"
                        label="描述"
                        width="500px">
                    </el-table-column>
                    <el-table-column
                        label="操作"
                        width="150px">
                      <template slot-scope="scope">
                        <el-button
                            size="mini"
                            @click="onEdit(scope.$index, scope.row)"
                            type="common">修改
                        </el-button>
                        <el-button
                            size="mini"
                            @click="handleDelete(scope.$index, scope.row)"
                            type="danger">删除
                        </el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-col>
              </el-row>
          </div>
        </el-col>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import ManagerAside from "@/components/Manager/ManagerAside";
import ManagerHeader from "@/components/Manager/ManagerHeader";
import request from "@/utils/request";
export default {
  name: "ManagerHome",
  components:{ManagerAside,ManagerHeader},
  created() {
    request.get("http://localhost:9090/user/count").then(res=>
        {
            this.value2=res;
        }
    );
    request.get("http://localhost:9090/user/count").then(res=>
        {
          this.value1=res;
        }
    );
    this.getAll();
  },
  data() {
    return {
      tableData:[{
        title:"",
        description:""
      }
      ],
      InformationForm: {
        title:"",
        description:""
      },
      InformationFromRules:{
        title: [
          {required: true, message: '请输入标题', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 1到 10个字符', trigger: 'blur'}
        ],
        description: [
          {required: true, message: '请输入描述', trigger: 'blur'},
          {min: 1, max:100, message: '长度在 1到 100个字符', trigger: 'blur'}
        ]
      },
      like: true,
      value1: 4154.564,
      value2: 2222,
      value3:222,
      title: "今年的增长",
      input: "Hello Element UI!",
      draw:false,
      draw2:false
    }
  },
  methods:
      {
        getAll()
        {
          request.get("http://localhost:9090/info/all").then(res=>{
            this.tableData=res;
          })
        },
        newInfo()
        {
          this.$refs["InformationFormRef"].validate(valid => {
                if (valid) {
                  request.post("http://localhost:9090/info/new", this.InformationForm).then(
                      res => {
                        this.$message({
                          type: 'success',
                          message: '新增成功'
                        });
                        this.getAll();
                        this.draw = false;
                      }
                  )
                }
                else
                {
                  this.$message({
                    type: 'info',
                    message: '请按指定内容新增'
                  });
                }
              })
        },
        newInfoButton()
        {
          this.draw=true;
        },
        onEdit(index,row)
        {
          this.draw2=true;
          this.InformationForm.id=row.id;
          this.InformationForm.title=row.title;
          this.InformationForm.description=row.description;
        },
        handleDelete(index,row)
        {
          this.$confirm('此操作将永久删除该项, 是否继续?', '提示', {
            cancelButtonText: '取消',
            confirmButtonText: '确定',
            type: 'warning'
          }).then(() => {
            this.deleteInfo(row.id);
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
        handleEdit(index,row)
        {
          this.$refs["InformationFormRef"].validate(valid => {
            if (valid) {
              request.post("http://localhost:9090/info/new", this.InformationForm).then(
                  res => {
                    this.$message({
                      type: 'success',
                      message: '修改成功'
                    });
                    this.getAll();
                    this.draw2= false;
                    this.InformationForm.id="";
                    this.InformationForm.title="";
                    this.InformationForm.description="";
                  }
              )
            }
            else
            {
              this.$message({
                type: 'info',
                message: '请按指定内容修改'
              });
            }
          })
        },
        deleteInfo(id){
          this.request.delete("http://localhost:9090/info/delete",{params:{id:id}})
              .then(res=>
              {
                this.getAll();
              })
        },
        onExit(){
          this.$message({
            type: 'info',
            message: '已取消新增'
          });
          this.draw=false;
        },
        onExitEdit(){
          this.$message({
            type: 'info',
            message: '已取消修改'
          });
          this.InformationForm.id="";
          this.InformationForm.title="";
          this.InformationForm.description="";
          this.draw2=false;
        }

      }
}
</script>

<style lang="less" scoped>
like {
  cursor: pointer;
  font-size: 25px;
  display: inline-block;
}
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

.rightContainer {
  position: relative;
  top: 2.5%;
  left: 2.5%;
  background-color: #ffffff;
  border-radius: 30px;
  width: 95%;
  height: 95%;
}

.changeForm {
  position: relative;
  left: 10%;
  top: 10%;
  width: 80%;
}

.table{
  position: relative;
  left: 2.5%;
  width: 95%;
  margin-top: 2.5%;
}
</style>