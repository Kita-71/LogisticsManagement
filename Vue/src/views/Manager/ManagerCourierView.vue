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
    <el-drawer
        title="新增快递点信息"
        :visible.sync="draw"
        direction="rtl"
        :close-on-click-modal="false"
        :wrapper-closable="false">
      <div class="leftContainer">
        <el-form ref="ChangeFormRef"
                 :model="InsertForm" :rules="ChangeFromRules" class="changeForm">
          <!-- 站点所在区域 -->
          <el-form-item label="站点所在区域" :required="true" prop="siteRegion">
            <el-input v-model="InsertForm.siteRegion" placeholder="站点所在区域" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点名称 -->
          <el-form-item label="站点名称" :required="true" prop="siteName">
            <el-input v-model="InsertForm.siteName" placeholder="站点名称" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点负责人姓名 -->
          <el-form-item label="站点负责人名称" :required="true" prop="sitePrincipleName">
            <el-input v-model="InsertForm.sitePrincipleName" placeholder="站点负责人名称" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点负责人地址 -->
          <el-form-item label="站点负责人手机号" :required="true" prop="sitePrinciplePhone">
            <el-input v-model="InsertForm.sitePrinciplePhone" placeholder="站点负责人手机号" size="medium">
            </el-input>
          </el-form-item>
<!--          &lt;!&ndash; 站点用户Id &ndash;&gt;-->
<!--          <el-form-item label="站点账号Id" :required="true" prop="siteUserId">-->
<!--            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium">-->
<!--            </el-input>-->
<!--          </el-form-item>-->
          <!-- 权限-->
          <el-form-item >
            <el-button type="primary" @click="onNew" >确定</el-button>
            <el-button @click="onNewExit">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
    <el-drawer
        title="修改快递点信息"
        :visible.sync="draw2"
        direction="rtl"
        :close-on-click-modal="false"
        :wrapper-closable="false">
      <div class="leftContainer">
        <el-form ref="ChangeFormRef"
                 :model="ChangeForm" :rules="ChangeFromRules" class="changeForm">
          <!-- 站点Id -->
          <el-form-item label="站点Id" :required="true" prop="siteId">
            <el-input v-model="ChangeForm.siteId" placeholder="站点Id" size="medium" :disabled="true">
            </el-input>
          </el-form-item>
          <!-- 站点所在区域 -->
          <el-form-item label="站点所在区域" :required="true" prop="siteRegion">
            <el-input v-model="ChangeForm.siteRegion" placeholder="站点所在区域" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点名称 -->
          <el-form-item label="站点名称" :required="true" prop="siteName">
            <el-input v-model="ChangeForm.siteName" placeholder="站点名称" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点负责人姓名 -->
          <el-form-item label="站点负责人名称" :required="true" prop="sitePrincipleName">
            <el-input v-model="ChangeForm.sitePrincipleName" placeholder="站点负责人名称" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点负责人地址 -->
          <el-form-item label="站点负责人手机号" :required="true" prop="sitePrinciplePhone">
            <el-input v-model="ChangeForm.sitePrinciplePhone" placeholder="站点负责人手机号" size="medium">
            </el-input>
          </el-form-item>
<!--          &lt;!&ndash; 站点用户Id &ndash;&gt;-->
<!--          <el-form-item label="站点账号Id" :required="true" prop="siteUserId">-->
<!--            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="true">-->
<!--            </el-input>-->
<!--          </el-form-item>-->
          <!-- 权限-->
          <el-form-item >
            <el-button type="primary" @click="onSubmit" >修改</el-button>
            <el-button @click="onExit">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
    <el-header >
      <ManagerHeader></ManagerHeader>
    </el-header>

    <el-container class="containerBottom">
      <el-aside class="aside" width="260px" >
        <ManagerAside></ManagerAside>
      </el-aside>
      <el-main>
        <el-col :span="24" >
          <div class="rightContainer">
            <el-row>
              <el-col :span="6">
                <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
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
                  <el-button slot="append" icon="el-icon-search" @click="clicksearch"></el-button>
                </el-input>
              </el-col>
              <el-col :span="6">
                <el-button type="primary" class="newButton" @click="this.newSite">新增快递点</el-button>
              </el-col>
            </el-row>

            <el-table
                :data="tableData"
                class="table"
                border
                height="85%"
            >
              <el-table-column
                  prop="siteId"
                  label="编号"
                  width="80">
              </el-table-column>
              <el-table-column
                  prop="siteRegion"
                  label="所在区域"
                  width="300">
              </el-table-column>
              <el-table-column
                  prop="siteName"
                  label="站点名称"
                  width="250">
              </el-table-column>
              <el-table-column
                  prop="sitePrincipleName"
                  label="负责人姓名"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="sitePrinciplePhone"
                  label="负责人手机号"
                  width="200">
              </el-table-column>
<!--              <el-table-column-->
<!--                  prop="siteUserId"-->
<!--                  label="站点用户账号ID"-->
<!--                  width="200">-->
<!--              </el-table-column>-->
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
                :page-sizes="[4, 8, 12,16]"
                :page-size="4"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                class="pagination">
            </el-pagination>
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
  data() {
    return {
      //与后端对接时时把该项直接覆盖即可
      tableData: [],
      ChangeForm: {
        siteId:"",
        siteRegion:"",
        siteName:"",
        sitePrincipleName:"",
        sitePrinciplePhone:""
      },
      InsertForm:{
        siteRegion:"",
        siteName:"",
        sitePrincipleName:"",
        sitePrinciplePhone:""
      },
      ChangeFromRules: {
        // 验证用户名是否合法
        sitePrinciplePhone:[
          {required: true, message: '请输入负责人手机号', trigger: 'blur'},
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
        siteUserId: [{required: true, message: '请输入站点所属用户ID', trigger: 'blur'}
        ],
        siteRegion:[{
          required: true, message: '请输入站点所在区域', trigger: 'blur'
        }],
        siteName:[{
          required: true, message: '请输入站点名称', trigger: 'blur'
        }],
        sitePrincipleName: [{
          required: true, message: '请输入站点负责人手机号', trigger: 'blur'
        }],
      },
      DialogA: false,
      DialogB: false,
      search_input: "",
      options: [
        {
          value: 'site_name',
          label: '站点名称'
        },
        {
          value: 'site_region',
          label: '所在区域'
        },
        {
          value: 'site_principle',
          label: '负责人姓名'
        },
      ],
      value: "site_name",//当前选择项
      draw2:false,
      draw:false,
      page_size:4,
      currentPage: 1,
      total:0,
      deleteSiteid:1,
    }
  },
  created(){
    this.getSiteTotal();
  },
  methods: {
    getSiteTotal(){
      this.request.get("http://localhost:9090/site/pagefilter",{params:{pageNum:this.currentPage,pageSize:this.page_size,searchMode: this.value,search_input: this.search_input}})
          .then(res=>
          {
            this.tableData=res.records;
            this.total=res.total;
          })
    },
    handleSizeChange(val){
      this.page_size=val;
      this.getSiteTotal();
    },
    handleCurrentChange(val){
      this.currentPage=val;
      this.getSiteTotal();
    },
    clicksearch(){
      this.currentPage=1;
      this.getSiteTotal();
    },
    deleteSite(){
      this.request.delete("http://localhost:9090/site/delete",{params:{id:this.deleteSiteid}})
          .then(res=>
          {
            this.getSiteTotal();
          })
    },
    onNew() {
      this.$refs["ChangeFormRef"].validate(valid => {
        if (valid) {
          request.post("http://localhost:9090/site/changeInfo",this.InsertForm).then(res=> {
            if (res)
            {
              this.$message({
                type: 'success',
                message: '新增成功'
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
          this.draw=false;
          this.InsertForm.siteRegion="";
          this.InsertForm.siteName="";
          this.InsertForm.sitePrincipleName="";
          this.InsertForm.sitePrinciplePhone="";
        }else{
          this.DialogB = true;
          console.log("error submit!!");
        }
      });
    },
    onNewExit()
    {
      this.$message({
        type: 'info',
        message: '已取消新增'
      });
      this.draw=false;
      this.ChangeForm.siteId="";
      this.ChangeForm.siteRegion="";
      this.ChangeForm.siteName="";
      this.ChangeForm.sitePrincipleName="";
      this.ChangeForm.sitePrinciplePhone="";
      this.ChangeForm.siteUserId="";
    },
    onSubmit() {
      this.$refs["ChangeFormRef"].validate(valid => {
        if (valid) {
          request.post("http://localhost:9090/site/changeInfo",this.ChangeForm).then(res=> {
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
      this.draw2=false;
      this.ChangeForm.siteId="";
      this.ChangeForm.siteRegion="";
      this.ChangeForm.siteName="";
      this.ChangeForm.sitePrincipleName="";
      this.ChangeForm.sitePrinciplePhone="";
      this.ChangeForm.siteUserId="";
    },
    handleEdit(index, row) {
      this.draw2=true;
      this.ChangeForm.siteId=row.siteId;
      this.ChangeForm.siteRegion=row.siteRegion;
      this.ChangeForm.siteName=row.siteName;
      this.ChangeForm.sitePrincipleName=row.sitePrincipleName;
      this.ChangeForm.sitePrinciplePhone=row.sitePrinciplePhone;
      this.ChangeForm.siteUserId=row.siteUserId;
    },
    handleDelete(index,row){
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteSiteid=Number(row.siteId);
        this.deleteSite();
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
    newSite()
    {
      this.draw=true;
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
.newButton{
  position: relative;
  top: 20px;
}

</style>