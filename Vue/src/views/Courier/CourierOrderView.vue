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
        title="修改订单信息"
        :visible.sync="draw2"
        direction="rtl"
        :close-on-click-modal="false"
        :wrapper-closable="false">
      <div class="leftContainer">
        <el-form ref="ChangeFormRef"
                 :model="ChangeForm" :rules="ChangeFromRules" class="changeForm">
          <!-- 站点Id -->
          <el-form-item label="订单Id" :required="true" prop="siteId">
            <el-input v-model="ChangeForm.orderId" placeholder="" size="medium" :disabled="true" >
            </el-input>
          </el-form-item>
          <!-- 站点所在区域 -->
          <el-form-item label="物品名" :required="true" prop="siteRegion">
            <el-input v-model="ChangeForm.siteRegion" placeholder="站点所在区域" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点名称 -->
          <el-form-item label="发件人" :required="true" prop="siteName">
            <el-input v-model="ChangeForm.siteName" placeholder="站点名称" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点负责人姓名 -->
          <el-form-item label="发件人电话" :required="true" prop="sitePrincipalName">
            <el-input v-model="ChangeForm.sitePrincipalName" placeholder="站点负责人名称" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点负责人地址 -->
          <el-form-item label="收件人" :required="true" prop="sitePrincipalPhone">
            <el-input v-model="ChangeForm.sitePrincipalPhone" placeholder="站点负责人手机号" size="medium">
            </el-input>
          </el-form-item>
          <!-- 站点用户Id -->
          <el-form-item label="收件人电话" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="收件人电话" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="收件地址" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="目前位置" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="状态" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="取件方式" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="预约时间" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="发货时间" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="抵达时间" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="备注" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="发货人uid" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <el-form-item label="收货人uid" :required="true" prop="siteUserId">
            <el-input v-model="ChangeForm.siteUserId" placeholder="站点账号Id" size="medium" :disabled="false">
            </el-input>
          </el-form-item>
          <!-- 权限-->
          <el-form-item >
            <el-button type="primary" @click="onSubmit" >确认修改</el-button>
            <el-button @click="onExit2">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
    <el-header >
      <CourierHeader></CourierHeader>
    </el-header>

    <el-container class="containerBottom">
      <el-aside class="aside" width="260px" >
        <CourierAside></CourierAside>
      </el-aside>
      <el-main>
        <el-col :span="24" >
          <div class="rightContainer">
            <el-row>
              <el-col :span="6">
                <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
                  <el-breadcrumb-item>物流订单管理</el-breadcrumb-item>
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
              <!--              <el-col :span="6">-->
              <!--                <el-button type="primary" class="newButton" @click="handleNew">新增订单</el-button>-->
              <!--              </el-col>-->
            </el-row>

            <el-table
                :data="tableData"
                class="table"
                border
                height="85%"
            >
              <el-table-column
                  prop="orderId"
                  label="订单号"
                  width="215">
              </el-table-column>
              <el-table-column
                  prop="goods"
                  label="物品名"
                  width="150">
              </el-table-column>
              <el-table-column
                  prop="senderName"
                  label="发件人"
                  width="130">
              </el-table-column>
              <el-table-column
                  prop="senderPhone"
                  label="发件人电话"
                  width="160">
              </el-table-column>
              <el-table-column
                  prop="receiverName"
                  label="收件人"
                  width="130">
              </el-table-column>
              <el-table-column
                  prop="receiverPhone"
                  label="收件人电话"
                  width="160">
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
import CourierAside from "@/components/Courier/CourierAside";
import CourierHeader from "@/components/Courier/CourierHeader";
export default {
  name: "CourierHome",
  components:{CourierAside,CourierHeader},
  data() {
    return {
      tableData:[],
      ChangeForm: {
        orderId:"",
        goods:"",
        origin:"",
        sender_name:"",
        sender_phone:"",
        dest:"",
        receiver_name:"",
        receiver_phone:"",
        current_site:"",
        state:"",
        pickup_methon:"",
        book_time:"",
        send_time:"",
        done_time:"",
        postscript:"",
        sender_uid:"",
        receiver_uid:""
      },
      ChangeFromRules: {
        // 验证用户名是否合法
        sitePrincipalPhone:[
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
        sitePrincipalName: [{
          required: true, message: '请输入站点负责人手机号', trigger: 'blur'
        }],
        sitePrincipalNull: [{
        }],
      },
      DialogA: false,
      DialogB: false,
      search_input: "",
      options: [
        {
          value: 'orderId',
          label: '订单号'
        },
        {
          value: 'goods',
          label: '物品名'
        },
        {
          value: 'sender_name',
          label: '发件人'
        },
        {
          value: 'sender_phone',
          label: '发件人电话'
        },
        {
          value: 'receiver_name',
          label: '收件人'
        },
        {
          value: 'receiver_phone',
          label: '收件人电话'
        },
      ],
      value: "orderId",//当前选择项
      draw2:false,
      draw:false,
      page_size:9,
      currentPage: 1,
      total:0,
      deleteOrderid:""
    }
  },
  created(){
    this.getOrderTotal();
  },
  methods: {
    getOrderTotal(){
      this.request.get("http://localhost:9090/order/pagefilter",{params:{pageNum:this.currentPage,pageSize:this.page_size,searchMode: this.value,search_input: this.search_input}})
          .then(res=>
          {
            this.tableData=res.records;
            this.total=res.total;
          })
    },
    handleSizeChange(val){
      this.page_size=val;
      this.getOrderTotal();
    },
    handleCurrentChange(val){
      this.currentPage=val;
      this.getOrderTotal();
    },
    clicksearch(){
      this.currentPage=1;
      this.getOrderTotal();
    },
    deleteOrder(){
      this.request.delete("http://localhost:9090/order/delete",{params:{id:this.deleteOrderid}})
          .then(res=>
          {
            this.getOrderTotal();
          })
    },
    onNew() {
      this.$refs["ChangeFormRef"].validate(valid => {
        if (valid) {
          console.log("success submit!!");
          this.$message({
            type: 'success',
            message: '新增成功'
          });
          this.draw=false;
          this.ChangeForm.orderId=row.orderId;
          this.ChangeForm.goods=row.goods;
          this.ChangeForm.origin="";
          this.ChangeForm.sender_name="";
          this.ChangeForm.sender_phone="";
          this.ChangeForm.dest="";
          this.ChangeForm.receiver_name="";
          this.ChangeForm.receiver_phone="";
          this.ChangeForm.current_site="";
          this.ChangeForm.state="";
          this.ChangeForm.pickup_methon="";
          this.ChangeForm.book_time="";
          this.ChangeForm.send_time="";
          this.ChangeForm.done_time="";
          this.ChangeForm.postscript="";
          this.ChangeForm.sender_uid="";
          this.ChangeForm.receiver_uid="";
        }else{
          this.DialogB = true;
          console.log("error submit!!");
        }
      });
    },
    onExit()
    {
      this.$message({
        type: 'info',
        message: '已取消新增'
      });
      this.draw=false;
      this.ChangeForm.orderId=row.orderId;
      this.ChangeForm.goods="";
      this.ChangeForm.origin="";
      this.ChangeForm.sender_name="";
      this.ChangeForm.sender_phone="";
      this.ChangeForm.dest="";
      this.ChangeForm.receiver_name="";
      this.ChangeForm.receiver_phone="";
      this.ChangeForm.current_site="";
      this.ChangeForm.state="";
      this.ChangeForm.pickup_methon="";
      this.ChangeForm.book_time="";
      this.ChangeForm.send_time="";
      this.ChangeForm.done_time="";
      this.ChangeForm.postscript="";
      this.ChangeForm.sender_uid="";
      this.ChangeForm.receiver_uid="";
    },
    onSubmit() {
      this.$refs["ChangeFormRef"].validate(valid => {
        if (valid) {
          console.log("success submit!!");
          this.$message({
            type: 'success',
            message: '修改成功'
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
    onExit2()
    {
      this.$message({
        type: 'info',
        message: '已取消修改'
      });
      this.draw2=false;
      this.ChangeForm.orderId="";
      this.ChangeForm.goods="";
      this.ChangeForm.origin="";
      this.ChangeForm.sender_name="";
      this.ChangeForm.sender_phone="";
      this.ChangeForm.dest="";
      this.ChangeForm.receiver_name="";
      this.ChangeForm.receiver_phone="";
      this.ChangeForm.current_site="";
      this.ChangeForm.state="";
      this.ChangeForm.pickup_methon="";
      this.ChangeForm.book_time="";
      this.ChangeForm.send_time="";
      this.ChangeForm.done_time="";
      this.ChangeForm.postscript="";
      this.ChangeForm.sender_uid="";
      this.ChangeForm.receiver_uid="";
    },
    handleNew(index, row){
      this.draw=true;
      /**/
      this.ChangeForm.orderId="";
      this.ChangeForm.goods="";
      this.ChangeForm.origin="";
      this.ChangeForm.sender_name="";
      this.ChangeForm.sender_phone="";
      this.ChangeForm.dest="";
      this.ChangeForm.receiver_name="";
      this.ChangeForm.receiver_phone="";
      this.ChangeForm.current_site="";
      this.ChangeForm.state="";
      this.ChangeForm.pickup_methon="";
      this.ChangeForm.book_time="";
      this.ChangeForm.send_time="";
      this.ChangeForm.done_time="";
      this.ChangeForm.postscript="";
      this.ChangeForm.sender_uid="";
      this.ChangeForm.receiver_uid="";
    },
    handleEdit(index, row) {
      this.draw2=true;
      /**/
      this.ChangeForm.orderId=row.orderId;
      this.ChangeForm.goods=row.goods;
      this.ChangeForm.origin="";
      this.ChangeForm.sender_name="";
      this.ChangeForm.sender_phone="";
      this.ChangeForm.dest="";
      this.ChangeForm.receiver_name="";
      this.ChangeForm.receiver_phone="";
      this.ChangeForm.current_site="";
      this.ChangeForm.state="";
      this.ChangeForm.pickup_methon="";
      this.ChangeForm.book_time="";
      this.ChangeForm.send_time="";
      this.ChangeForm.done_time="";
      this.ChangeForm.postscript="";
      this.ChangeForm.sender_uid="";
      this.ChangeForm.receiver_uid="";

    },
    handleDelete(index,row){
      this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteOrderid=row.orderId;
        this.deleteOrder();
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
  left: 2%;
  top: 0;
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