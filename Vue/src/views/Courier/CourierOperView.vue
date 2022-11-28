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
        <el-descriptions class="margin-top" column="1" style="position: relative; font-size: large; left: -5%">
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

<!--          <el-descriptions-item>-->
<!--            <template slot="label">-->
<!--              <i class="el-icon-location-outline"></i>-->
<!--              <div  style="color:#4d52f8">-->
<!--                取件方式-->
<!--              </div>-->
<!--            </template>-->
<!--            {{this.infoForm.pickup_method}}-->
<!--          </el-descriptions-item>-->
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
          <div  class="rightContainer">

            <el-row >
              <el-col :span="6">
                <el-breadcrumb separator-class="el-icon-arrow-right" class="bread">
                  <el-breadcrumb-item :to="{ path: '/CourierHome' }">主页</el-breadcrumb-item>
                  <el-breadcrumb-item>物流操作</el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </el-row>
            <el-row style="top: 6%" >
              <div style="position: relative;top: -22px;">
                <el-col :span="12" style=" margin-left: 10px; width: 47%">
                  <el-input v-model="takeininput" placeholder="请输入待入库的订单号"  clearable class="textarea"></el-input>
                </el-col>
                <el-col :span="11">
                  <el-button type="primary" class="do_button" @click="handleInput" >入库</el-button>
                </el-col>
              </div>

            </el-row>
            <el-row class="choose_header" gutter="20">
              <el-col  :span="12" class="search_col1">
                <el-col :span="4">
                  <el-select v-model="value1" placeholder="请选择" class="searchSelect1">
                    <el-option
                        v-for="item in options1"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    >
                    </el-option>
                  </el-select>
                </el-col>
                <el-col :span="12">
                  <el-input
                      placeholder= "请输入与选择框对应的查询内容"
                      v-model="search_input1"
                      class="search">
                    <el-button slot="append" icon="el-icon-search" @click="clicksearch1"></el-button>
                  </el-input>
                </el-col>
              </el-col>
              <el-col  :span="12" class="search_col2">
                <el-col :span="4">
                  <el-select v-model="value2" placeholder="请选择" class="searchSelect2">
                    <el-option
                        v-for="item in options2"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    >
                    </el-option>
                  </el-select>
                </el-col>
                <el-col :span="12">
                  <el-input
                      placeholder= "请输入与选择框对应的查询内容"
                      v-model="search_input2"
                      class="search">
                    <el-button slot="append" icon="el-icon-search" @click="clicksearch2"></el-button>
                  </el-input>
                </el-col>
              </el-col>
            </el-row>

            <el-row :gutter="20" class="table_row">
              <el-col :span="13" >
                <el-table
                    :data="tableData1"
                    class="table"
                    :header-cell-style="{ backgroundColor: '#fafafa',   textAlign: 'center',  }"
                    border
                    height="85%"
                    style="margin-left: 10px"

                >
                  <el-table-column label="已入库待派送" >

                    <el-table-column
                        prop="orderId"
                        label="订单id">
                    </el-table-column>
                    <el-table-column
                        prop="goods"
                        label="物品名">
                    </el-table-column>
                    <el-table-column
                        prop="receiverName"
                        width="91%"
                        label="收件人">
                    </el-table-column>
                    <el-table-column
                        prop="receiverPhone"
                        width="115%"
                        label="收件人电话">
                    </el-table-column>
                    <el-table-column
                        prop="dest"
                        label="收件人地址">
                    </el-table-column>
                    <el-table-column
                        width="145%"
                        label="操作">
                      <template slot-scope="scope">
                        <el-button
                            size="mini"
                            @click="handleDispatch(scope.$index, scope.row)">派送
                        </el-button>
                        <el-button
                            size="mini"
                            @click="handleInfo(scope.$index, scope.row)">详情
                        </el-button>
                      </template>
                    </el-table-column>
                  </el-table-column>
                </el-table>
              </el-col>
              <el-col :span="11">
                <el-table
                    :data="tableData2"
                    class="table"
                    border
                    :header-cell-style="{ backgroundColor: '#fafafa',   textAlign: 'center',  }"
                    height="85%"
                    style="width: 99%"
                >
                  <el-table-column label="已派送" >
                    <el-table-column
                        width="150%"
                        prop="orderId"
                        label="订单id">
                    </el-table-column>
                    <el-table-column
                        prop="goods"
                        label="物品名">
                    </el-table-column>
                    <el-table-column
                        prop="receiverName"
                        width="90%"
                        label="收件人">
                    </el-table-column>
                    <el-table-column
                        prop="receiverPhone"
                        width="115%"
                        label="收件人电话">
                    </el-table-column>
<!--                    <el-table-column-->
<!--                        prop="state"-->
<!--                        label="状态">-->

<!--                    </el-table-column>-->
                    <el-table-column
                        width="80%"
                        label="操作">
                      <template slot-scope="scope">
                        <el-button
                            size="mini"
                            @click="handleInfo(scope.$index, scope.row)">详情
                        </el-button>
                      </template>
                    </el-table-column>


                  </el-table-column>

                </el-table>
              </el-col>
            </el-row>
            <el-pagination
                @size-change="handleSizeChange1"
                @current-change="handleCurrentChange1"
                :current-page="currentPage4"
                :page-sizes="[4, 8, 12,16]"
                :page-size="4"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total1"
                class="pagination1">
            </el-pagination>
            <el-pagination
                @size-change="handleSizeChange2"
                @current-change="handleCurrentChange2"
                :current-page="currentPage4"
                :page-sizes="[4, 8, 12,16]"
                :page-size="4"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total2"
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
  name: "CourierOperView",
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
      takeininput:'',
      // tableData1: [
      //   {
      //     orderId:"123123123123",
      //     goods:"医托答辩",
      //     origin:"540",
      //     sender_name:"Kita",
      //     sender_phone:"13126078008",
      //     dest:"电子科技大学本科六组图22栋540室",
      //     receiver_name:"Kita",
      //     receiver_phone:"13126078008",
      //     current_site: "",
      //     state: "23-2-1882",
      //     pickup_method:"",
      //     book_time: "2022-11-26-17:02",
      //     send_time: "",
      //     done_time: "",
      //     postscript: "哈哈",
      //     sender_uid: "12",
      //     receiver_uid: "134"
      //   },
      //   {
      //     orderId:"234234234234",
      //     goods:"良托答辩",
      //     origin:"540",
      //     sender_name:"Kita",
      //     sender_phone:"13126078008",
      //     dest:"电子科技大学本科六组图22栋540室",
      //     receiver_name:"Kita",
      //     receiver_phone:"13126078008",
      //     current_site: "",
      //     state: "22-5-2331",
      //     pickup_method:"",
      //     book_time: "2022-11-26-19:35",
      //     send_time: "",
      //     done_time: "",
      //     postscript: "",
      //     sender_uid: "",
      //     receiver_uid: ""
      //   },{
      //     orderId:"123123123123",
      //     goods:"伞拖答辩",
      //     origin:"540",
      //     sender_name:"Kita",
      //     sender_phone:"13126078008",
      //     dest:"电子科技大学本科六组图22栋540室",
      //     receiver_name:"Kita",
      //     receiver_phone:"13126078008",
      //     current_site: "",
      //     state: "23-2-1882",
      //     pickup_method:"",
      //     book_time: "2022-11-26-17:02",
      //     send_time: "",
      //     done_time: "",
      //     postscript: "",
      //     sender_uid: "",
      //     receiver_uid: ""
      //   },
      //   {
      //     orderId:"234234234234",
      //     goods:"斯托答辩",
      //     origin:"540",
      //     sender_name:"Kita",
      //     sender_phone:"13126078008",
      //     dest:"电子科技大学本科六组图22栋540室",
      //     receiver_name:"Kita",
      //     receiver_phone:"13126078008",
      //     current_site: "",
      //     state: "22-5-2331",
      //     pickup_method:"",
      //     book_time: "2022-11-26-19:35",
      //     send_time: "",
      //     done_time: "",
      //     postscript: "",
      //     sender_uid: "",
      //     receiver_uid: ""
      //   }
      // ],
      // tableData2: [
      //     {
      //       orderId:"123123123123",
      //       goods:"武拓答辩",
      //       origin:"540",
      //       sender_name:"Kita",
      //       sender_phone:"13126078008",
      //       dest:"电子科技大学本科六组图22栋540室",
      //       receiver_name:"Kita",
      //       receiver_phone:"13126078008",
      //       current_site: "",
      //       state: "23-2-1882",
      //       pickup_method:"",
      //       book_time: "2022-11-26-17:02",
      //       send_time: "",
      //       done_time: "",
      //       postscript: "",
      //       sender_uid: "",
      //       receiver_uid: ""
      //     },
      //     {
      //         orderId:"234234234234",
      //         goods:"刘拓答辩",
      //         origin:"540",
      //         sender_name:"Kita",
      //         sender_phone:"13126078008",
      //         dest:"电子科技大学本科六组图22栋540室",
      //         receiver_name:"Kita",
      //         receiver_phone:"13126078008",
      //         current_site: "",
      //         state: "22-5-2331",
      //         pickup_method:"",
      //         book_time: "2022-11-26-19:35",
      //         send_time: "",
      //         done_time: "",
      //         postscript: "",
      //         sender_uid: "",
      //         receiver_uid: ""
      //       }
      //
      // ],
      tableData1: [],
      tableData2: [],
      ChangeForm: {
        username:"",
        phone: "",
        email: "",
        permission:""
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
      registerForm:{
        orderId: "",
        currentSite: 0,
        state: "pending_pickup"
      },
      dispatchForm:{
        orderId: "",
        state:"done"
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
      options1: [
          {
        value: 'orderId',
        label: '订单ID'
      }, {
        value: 'goods',
        label: '物品名'
      }, {
        value: 'receiver_name',
        label: '收件人'
      }, {
        value: 'receiver_phone',
        label: '收件人电话'
      }, {
        value: 'dest',
        label: '收件人地址'
      }
      ],
      options2: [
          {
        value: 'orderId',
        label: '订单ID'
      }, {
        value: 'goods',
        label: '物品名'
      }, {
        value: 'receiver_name',
        label: '收件人'
      }, {
        value: 'receiver_phone',
        label: '收件人电话'
      }
      // , {
      //   value: 'state',
      //   label: '状态'
      // }
      ],
      value1:"orderId",
      value2:"orderId",
      search_input1:"",
      search_input2:"",
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
      page_size1:4,
      currentPage1: 1,
      page_size2:4,
      currentPage2: 1,
      total1:0,
      total2:0,
    }
  },
  created() {
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
            this.getSiteOrder();
            this.getSiteOrderDone();
          })
      console.log(this.site_id);
    },
    getSiteOrder(){
      this.request.get("http://localhost:9090/order/getsiteorder",{params:{pageNum:this.currentPage1,pageSize:this.page_size1,siteId:this.site_id,searchMode: this.value1,search_input: this.search_input1}})
          .then(res=>
          {
            this.tableData1=res.records;
            this.total1=res.total;
          })
    },
    handleSizeChange1(val){
      this.page_size1=val;
      this.getSiteOrder();
    },
    handleCurrentChange1(val){
      this.currentPage1=val;
      this.getSiteOrder();
    },
    getSiteOrderDone(){
      this.request.get("http://localhost:9090/order/getsiteorderdone",{params:{pageNum:this.currentPage2,pageSize:this.page_size2,siteId:this.site_id,searchMode: this.value2,search_input: this.search_input2}})
          .then(res=>
          {
            this.tableData2=res.records;
            this.total2=res.total;
          })
    },
    handleSizeChange2(val){
      this.page_size2=val;
      this.getSiteOrderDone();
    },
    handleCurrentChange2(val){
      this.currentPage2=val;
      this.getSiteOrderDone();
    },
    clicksearch1(){
      this.getSiteOrder();
    },
    clicksearch2(){
      this.getSiteOrderDone();
    },
    signUp() {
          request.post("http://localhost:9090/user/signUp",this.RegisterForm).then(res=>
              {
                if(res["user_exist"]==true)
                {
                  this.$message({
                    type: 'warning',
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
    },
    registerorder(){
      this.request.get("http://localhost:9090/order/checkorder",{params:{orderId:this.takeininput}}).then(res=>
          {
            if(res==true){//输入订单id存在
              //判断是否已经入库
              this.request.get("http://localhost:9090/order/checkexist",{params:{orderId:this.takeininput,current_site:this.site_id}})
                  .then(res=>
                  {
                    if(res==true){
                      //订单已入库
                      this.$message({
                        type: 'warning',
                        message: '该订单已入库'
                      });
                      this.takeininput="";
                    }else{
                      //订单未入库
                      this.registerForm.orderId=this.takeininput;
                      this.registerForm.currentSite=this.site_id;
                      this.request.post("http://localhost:9090/order/newOrUpdateOrder",this.registerForm)
                          .then(res=>
                          {
                            if(res==true){
                              this.$message({
                                type: 'success',
                                message: '入库成功'
                              });
                            }
                            this.getSiteOrder();
                            this.takeininput="";
                          })
                    }
                  })


            }else{
              this.$message({
                type: 'warning',
                message: '该订单ID不存在'
              });
              this.takeininput="";
            }
          })
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
    onExit() {
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
    onPasswdChange() {
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
    onExitPasswdChange() {
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
    handleDispatch(index,row){
      this.$confirm('是否确认派送?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() =>{
        this.dispatchForm.orderId=row.orderId;
        this.request.post("http://localhost:9090/order/newOrUpdateOrder",this.dispatchForm)
            .then(res=>
            {
              if(res==true){
                this.$message({
                  type: 'success',
                  message: '派送成功'
                });
              }
              this.getSiteOrder();
              this.getSiteOrderDone();
            })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消派送'
        });
      });
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
    handleInput()
    {
      this.$confirm('确定要入库吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.registerorder();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消入库'
        });
      });

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

    handleFalse() {
      //终止
      this.$message({
        type: 'info',
        message: '密码框禁止复制粘贴'
      });
      return false;
    },
    search1()
    {
      this.$message({
        type: 'success',
        message: '搜索已提交'
      });
    },search2()
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
.pagination1
{
  position: absolute;
  top: 95%;
  left:0%;
}
.do_button{

  position: relative;
  top: 95%;
  left:3%;
}
.pagination2
{
  position: absolute;
  top: 95%;
  left:50%;
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
.bread
{
  position: relative;
  font-size: 15px;
  top: 22px;
  left: 7%;
}
.table_row{
  position: relative;
  top: 7%;
}
.choose_header{
  position: relative;
  font-size: 15%;
  top: 4.5%;
  left: 2%;

}
.leftContainer{

  position: relative;
  font-size: 15px;
  top: 15px;
  left: 10%;
}
.search_col1{
  position: relative;
  font-size: 15px;
  top: 0%;
  left: -2%;
}
.search_col2{
  position: relative;
  font-size: 15px;
  top: 0%;
  left: 2%;
}
</style>