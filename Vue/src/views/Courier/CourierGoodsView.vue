<template>
  <el-container class="containerAll">
    <el-drawer
        :visible.sync="draw2"
        direction="rtl"
        :close-on-click-modal="false"
        :wrapper-closable="false">
      <template slot="title" style="text-align: left">
        <i class="el-icon-s-management">
          订单详细信息</i>
      </template>
      <div class="leftContainer">
        <el-descriptions class="margin-top" column="1">
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                订单ID
              </div>
            </template>
            {{this.infoForm.orderId}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                预定时间
              </div>

            </template>
            {{this.infoForm.book_time}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                物品名
              </div>
            </template>
            {{this.infoForm.goods}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                发件人姓名
              </div>
            </template>
            {{this.infoForm.sender_name}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                发件人手机号
              </div>
            </template>
            {{this.infoForm.sender_phone}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                发件人uid
              </div>

            </template>
            {{this.infoForm.sender_uid}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                收件人
              </div>
            </template>
            {{this.infoForm.receiver_name}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                收件人手机号
              </div>
            </template>
            {{this.infoForm.receiver_phone}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                收件人uid
              </div>

            </template>
            {{this.infoForm.receiver_uid}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                目的地
              </div>
            </template>
            {{this.infoForm.dest}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                发货地
              </div>
            </template>
            {{this.infoForm.origin}}
          </el-descriptions-item>

          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                取件方式
              </div>
            </template>
            {{this.infoForm.pickup_method}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
                状态
              </div>
            </template>
            {{this.infoForm.state}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              <div  style="color:#4d52f8">
              备注
              </div>
            </template>
            {{this.infoForm.postscript}}
          </el-descriptions-item>
        </el-descriptions>
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
            <el-row style=" top: 3%;left: 2.2%;">
              <el-col :span="6">
                <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
                  <el-breadcrumb-item :to="{ path: '/CourierHome' }">主页</el-breadcrumb-item>
                  <el-breadcrumb-item>预约送货</el-breadcrumb-item>
                </el-breadcrumb></el-col>
            </el-row>
            <el-row class="search_col">
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
            </el-row>
            <el-row style="position: relative; height:100%;">
              <el-table
                  :data="tableData"
                  border
                  height="85%"
                  style="position:absolute;width: 96% ; top:10%;margin-left: 2%;"
              >
                <el-table-column
                    prop="orderId"
                    label="订单ID">
                </el-table-column>
                <el-table-column
                    prop="goods"
                    label="物品名">
                </el-table-column>
                <el-table-column
                    prop="origin"
                    label="发货地">
                </el-table-column>
                <el-table-column
                    prop="senderName"
                    label="发件人姓名">
                </el-table-column>
                <el-table-column
                    prop="senderPhone"
                    label="发件人手机号">
                </el-table-column>
                <el-table-column
                    prop="bookTime"
                    label="预约时间">
                </el-table-column>
                <el-table-column
                    label="操作">
                  <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="success"
                        @click="handleDeliver(scope.$index, scope.row)">发货
                    </el-button>
                    <el-button
                        size="mini"
                        type="info"
                        round
                        @click="handleInfo(scope.$index, scope.row)">详情
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-row>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[8, 16, 24,32]"
                :page-size="8"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
                class="pagination2">
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
      // tableData: [{
      //   orderId:"123123123123",
      //   goods:"医托答辩",
      //   origin:"540",
      //   sender_name:"Kita",
      //   sender_phone:"13126078008",
      //   dest:"",
      //   receiver_name:"",
      //   receiver_phone:"",
      //   current_site: "",
      //   state: "",
      //   pickup_method:"",
      //   book_time: "2022-11-26-17:02",
      //   send_time: "",
      //   done_time: "",
      //   postscript: "",
      //   sender_uid: "",
      //   receiver_uid: ""
      // }],
      tableData: [],
      ChangeForm: {
        orderId:"",
        goods:"",
        origin:"",
        sender_name:"",
        sender_phone:"",
        dest:"",
        receiver_name:"",
        receiver_phone:"",
        current_site: "",
        state: "",
        pickup_method:"",
        book_time: "",
        send_time: "",
        done_time: "",
        postscript: "",
        sender_uid: "",
        receiver_uid: ""
      },
      infoForm:{
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
        pickup_method:"",
        book_time:"",
        send_time:"",
        done_time:"",
        postscript:"",
        sender_uid:"",
        receiver_uid:""
      },
      deliveryForm:{
        orderId: "",
        state: "in_transport"
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
      options: [
          {
        value: 'orderId',
        label: '订单ID'
      }, {
        value: 'goods',
        label: '物品名'
      }, {
        value: 'origin',
        label: '发货地'
      }, {
        value: 'sender_name',
        label: '发件人姓名'
      }, {
        value: 'sender_phone',
        label: '发件人手机号'
      }, {
        value: 'book_time',
        label: '预约时间'
      }],
      value:"orderId",
      draw:false,
      draw2:false,
      DialogA:false,
      username:"",
      user:{
        "userid": 0,
        "username": "",
        "nickname": "",
        "email": "",
        "phone": "",
        "address": "",
        "permission": "",
        "siteId": 0
      },
      site_id:0,
      page_size:4,
      currentPage: 1,
      total:0
    }
  },
  created(){
    this.getsiteid();
  },
  methods: {
    getsiteid(){
      this.username=this.$store.state.courier.username;
      this.request.get("http://localhost:9090/user/getone", {params:{username:this.username}})
          .then(res=>
          {
            this.user=res;
            this.site_id=this.user.siteId;
            this.getReserveOrder();
          })
      console.log(this.site_id);
    },
    getReserveOrder(){
      this.request.get("http://localhost:9090/order/getreserveorder",{params:{pageNum:this.currentPage,pageSize:this.page_size,siteId:this.site_id,searchMode: this.value,search_input: this.search_input}})
          .then(res=>
          {
            this.tableData=res.records;
            this.total=res.total;
          })
    },
    handleSizeChange(val){
      this.page_size=val;
      this.getReserveOrder();
    },
    handleCurrentChange(val){
      this.currentPage=val;
      this.getReserveOrder();
    },
    clicksearch(){
      this.$message({
        type: 'success',
        message: '搜索成功'
      });
      this.getReserveOrder();
    },
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
      this.ChangeForm.origin="";
      this.ChangeForm.book_time="";
      this.ChangeForm.dest="";
      this.ChangeForm.current_site="";
      this.ChangeForm.goods="";
      this.ChangeForm.done_time="";
      this.ChangeForm.orderId="";
      this.ChangeForm.pickup_method="";
      this.ChangeForm.postscript="";
      this.ChangeForm.receiver_name="";
      this.ChangeForm.receiver_phone="";
      this.ChangeForm.receiver_uid="";
      this.ChangeForm.send_time="";
      this.ChangeForm.sender_name="";
      this.ChangeForm.sender_uid="";
      this.ChangeForm.sender_phone="";

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
    handleInfo(index, row) {
      this.draw2=true;
      var orderId=row.orderId;
      this.request.get("http://localhost:9090/order/getorder",{params:{orderId:orderId}})
          .then(res=>
          {
            this.infoForm.orderId=res.orderId;
            this.infoForm.origin=res.origin;
            this.infoForm.book_time=res.bookTime;
            this.infoForm.dest=res.dest;
            this.infoForm.current_site=res.currentSite;
            this.infoForm.goods=res.goods;
            this.infoForm.done_time=res.doneTime;
            this.infoForm.pickup_method=res.pickupMethod;
            this.infoForm.postscript=res.postscript;
            this.infoForm.receiver_name=res.receiverName;
            this.infoForm.receiver_phone=res.receiverPhone;
            this.infoForm.receiver_uid=res.receiverUid;
            this.infoForm.send_time=res.sendTime;
            this.infoForm.sender_name=res.senderName;
            this.infoForm.sender_uid=res.senderUid;
            this.infoForm.sender_phone=res.senderPhone;
            this.infoForm.state=res.state;
          })


    },
    handleDeliver(index, row) {
      this.deliveryForm.orderId=row.orderId;
      this.request.post("http://localhost:9090/order/newOrUpdateOrder",this.deliveryForm)
          .then(res=>
          {
            if(res==true){
              this.$message({
                type: 'success',
                message: '发货成功'
              });
            }
            this.getReserveOrder();
          })
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
.margin-top{

  position: relative;
  left: 3%;
  font-size: large;
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
.search_col{
  position: relative;
  top: 5.5%;
  left: 2%;
}
</style>