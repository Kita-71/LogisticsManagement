<template>
  <el-container class="container1">
    <el-dialog title="添加接收人信息" :visible.sync="receiverVisible"  :close-on-click-modal="false" @close="" :show-close="false" width="90%">
      <el-form :model="receiverForm" :inline="true" ref="receiverFormRef">
        <el-row :gutter="10" v-for="(item,index) in receiverForm.object" :key="index">
          <el-col :span="2">
            <div style="text-align:right;">姓名</div>
          </el-col>
          <el-col :span="3">
            <el-form-item :prop="'object.' + index + '.name'" :rules="[{ trigger:'blur',message:'不能为空（必填项）',required:true }]">
              <el-input type="text" v-model="item.name" placeholder="请输入接收人姓名" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="2">
            <div style="text-align:right;">手机号</div>
          </el-col>
          <el-col :span="3">
            <el-form-item :prop="'object.' + index + '.phone'" :rules="[{ trigger:'blur',message:'不能为空（必填项）',required:true },{trigger:'change',min:11,max:11,messgae:'请输入有效的11位手机号'}]">
              <el-input type="phone" v-model="item.phone" placeholder="有效的11位手机号" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="2">
            <div style="text-align:right;">地址</div>
          </el-col>
          <el-col :span="10">
            <el-col :span="11">
              <el-form-item :prop="'object.' + index + '.region'">
              <region-group
                  :town="true"
                  v-model="item.region"
                  @change="receiversRegionChange($event,index)"
                  class="regionBox"
              />
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item :prop="'object.' + index + '.address'" :rules="[{ trigger:'blur',message:'不能为空（必填项）',required:true }]">
                <el-input type="phone" v-model="item.address" placeholder="有效地址" clearable></el-input>
              </el-form-item>
            </el-col>
          </el-col>
          <el-col :span="1">
            <div style="text-align:right;">
              <el-button type="text" plain circle v-if="receiverForm.object.length > 1" @click="removeRow(index)"><span style="color:red;">删</span></el-button>
            </div>
          </el-col>
          <el-col :span="1"></el-col>
        </el-row>
      </el-form>
      <el-row :gutter="10">
        <el-col :span="12">
          <div>
            <el-button type="text" @click="addRow()" class="el-icon-user" circle>添加更多收件人信息</el-button>
          </div>
        </el-col>
        <el-col :span="12">
          <div style="text-align:right;">
            <el-button type="primary" plain round @click="allSubmit('object')">保存</el-button>
          </div>
        </el-col>
      </el-row>
    </el-dialog  >
    <!-- 顶栏 -->
    <el-header height="60px">
      <UserHeader></UserHeader>
    </el-header>
    <el-main>
      <!-- 第一行布局 -->
      <el-row>
        <el-col :span="24" class="colPageHeader">
          <el-page-header class="pageheader" @back="goBack" content="预约发货">
          </el-page-header>
        </el-col>
      </el-row>
      <!-- 第二行栅格布局 -->
      <el-row style="height: 100%;width:80%;left: 10%">
        <el-col  :span="24" style="height: 100%">
          <div class="rightContainer">
            <el-row :gutter="20" style="height: 100%">
              <el-col  :span="4" style="height: 100%">
                <el-row>
                  <div class="logo">
                    <img src="@/assets/logo.png" class="logoimg">
                  </div>
                </el-row>
                <el-row>
                  <el-select class="options" v-model="value" placeholder="请选择">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                  </el-select>
                </el-row>
                <el-row>
                </el-row>
              </el-col>
              <el-col  :span="20" style="height: 100%">
                <div class="commitForm" v-if="value=='single'">
                <el-form ref="FormRef" :model="formData"  label-width="140px" :label-position="right" :rules="FromRules">
                  <el-form-item label="站点选择" :required="true" >
                    <el-col :span="11">
                      <region-group
                          separator="-"
                          :town="true"
                          v-model="region"
                          @change="siteRegionChange"
                          class="regionBox"
                      />
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                      <el-select v-model="site_value" filterable placeholder="请选择">
                        <el-option
                            v-for="item in site_options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                      </el-select>
                    </el-col>
                  </el-form-item>
                  <el-form-item label="发件人姓名" :required="true" prop="senderName">
                    <el-input v-model="formData.senderName" placeholder="请输入发件人姓名"></el-input>
                  </el-form-item>
                  <el-form-item label="发件人手机号" :required="true" prop="senderPhone">
                    <el-input v-model="formData.senderPhone" placeholder="请输入发件人手机号"></el-input>
                  </el-form-item>
                  <el-form-item label="发件人所在地" :required="true" prop="origin">
                      <el-col :span="11">
                        <region-group
                            separator="-"
                            :town="true"
                            v-model="senderRegion"
                            @change="senderRegionChange"
                            class="regionBox"
                        />
                      </el-col>
                      <el-col class="line" :span="2">-</el-col>
                      <el-col :span="11">
                        <el-input v-model="formData.origin" class="searchBox"></el-input>
                      </el-col>
                  </el-form-item>
                  <el-form-item label="收件人姓名" :required="true" prop="receiverName">
                    <el-input v-model="formData.receiverName" placeholder="请输入收件人姓名" ></el-input>
                  </el-form-item>
                  <el-form-item label="收件人手机号" :required="true" prop="receiverPhone">
                    <el-input v-model="formData.receiverPhone" placeholder="请输入收件人手机号" ></el-input>
                  </el-form-item>
                  <el-form-item label="收件人所在地" :required="true" prop="dest">
                    <el-col :span="11">
                      <region-group
                          separator="-"
                          :town="true"
                          v-model="receiverRegion"
                          @change="receiverRegionChange"
                          class="regionBox"
                      />
                    </el-col>
                    <el-col class="line" :span="2">-</el-col>
                    <el-col :span="11">
                      <el-input v-model="formData.dest" class="searchBox"></el-input>
                    </el-col>
                  </el-form-item>
                  <el-form-item label="货物类型" :required="true" prop="goods">
                    <el-input v-model="formData.goods"></el-input>
                  </el-form-item>
                  <el-form-item label="备注">
                    <el-input v-model="formData.postscript"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit" style="position: relative;left: 50%;width: 30%;height:5%;transform: translate(-50%);margin-top: 20px">立即寄件</el-button>
                  </el-form-item>
                </el-form>
                </div>
                <div class="commitForm" v-else-if="value=='many'">
                  <el-form ref="FormRef" :model="formData"  label-width="140px" :label-position="right" :rules="FromRules">
                    <el-form-item label="站点选择" :required="true" >
                      <el-col :span="11">
                        <region-group
                            separator="-"
                            :town="true"
                            v-model="region"
                            @change="siteRegionChange"
                            class="regionBox"
                        />
                      </el-col>
                      <el-col class="line" :span="2">-</el-col>
                      <el-col :span="11">
                        <el-select v-model="site_value" filterable placeholder="请选择">
                          <el-option
                              v-for="item in site_options"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value">
                          </el-option>
                        </el-select>
                      </el-col>
                    </el-form-item>
                    <el-form-item label="发件人姓名" :required="true" prop="senderName">
                      <el-input v-model="formData.senderName" placeholder="请输入发件人姓名"></el-input>
                    </el-form-item>
                    <el-form-item label="发件人手机号" :required="true" prop="senderPhone">
                      <el-input v-model="formData.senderPhone" placeholder="请输入发件人手机号"></el-input>
                    </el-form-item>
                    <el-form-item label="发件人所在地" :required="true" prop="origin">
                      <el-col :span="11">
                        <region-group
                            separator="-"
                            :town="true"
                            v-model="senderRegion"
                            @change="senderRegionChange()"
                            class="regionBox"
                        />
                      </el-col>
                      <el-col class="line" :span="2">-</el-col>
                      <el-col :span="11">
                        <el-input v-model="formData.origin" class="searchBox"></el-input>
                      </el-col>
                    </el-form-item>
                    <el-form-item label="收件人信息" >
                      <el-button type="primary" @click="receiverVisible = true" plain >已添加{{this.receiverForm.object.length}}项收件人信息</el-button>
                    </el-form-item>
                    <el-form-item label="货物类型" :required="true" prop="goods">
                      <el-input v-model="formData.goods"></el-input>
                    </el-form-item>
                    <el-form-item label="备注">
                      <el-input v-model="formData.postscript"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="onSubmit" style="position: relative;left: 50%;width: 30%;height:5%;transform: translate(-50%);margin-top: 20px">立即寄件</el-button>
                    </el-form-item>
                  </el-form>
                </div>
              </el-col>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import UserHeader from "@/components/User/UserHeader";
import { RegionGroup } from 'v-region'
import request from "@/utils/request";
export default {
  components: {UserHeader ,RegionGroup},
  name: "UserOrderView",
  data() {
    return {
      //
      receiverVisible:false,    //接收人弹出框
      worksiteForm:{
      },
      receiverForm:{
        object:[],
      },
      //
      userdata:{
      },
      //状态选择框
      options: [{
        value: 'single',
        label: '普通寄件'
      },
        {
          value: 'many',
          label: '批量寄件'
        }],
      value: 'single',
      site_options:[
      ],
      site_value: '',
      region: {
      },
      senderRegion:{},
      receiverRegion:{},
      siteRegionData:"",
      senderRegionData:"",
      receiverRegionData:"",
      receiversRegionData:[],
      formData: {
        goods:"",
        origin:"",
        senderName:"",
        senderPhone:"",
        dest:"",
        receiverName:"",
        receiverPhone:"",
        currentSite:0,
        state:"reserve",
        postscript:"",
        senderUid:0,
        receiverUid:0,
      },
      FromRules:{
        senderName: [
          {required: true, message: '发件人姓名不能为空', trigger: 'blur'}
        ],
        receiverName: [
          {required: true, message: '收件人姓名不能为空', trigger: 'blur'}
        ],
        origin:[
            {required: true, message: '始发地不能为空', trigger: 'blur'}
        ],
        dest: [
          {required: true, message: '目的地不能为空', trigger: 'blur'}
        ],
        goods:[
          {required: true, message: '货物类型不能为空', trigger: 'blur'}
        ],
        senderPhone: [
          {required: true, message: '发件人手机号不能为空', trigger: 'blur'},
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
        receiverPhone: [
          {required: true, message: '收件人手机号不能为空', trigger: 'blur'},
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
      }
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
  methods: {
    //
    removeRow(index){
      this.receiverForm.object.splice(index,1)
    },

    //添加接收人信息行
    addRow(){
      this.receiverForm.object.push({
        name:'',
        phone:'',
        email:'',
      })
    },
    //提交所有接收人信息
    allSubmit(){
      this.$refs.receiverFormRef.validate(valid => {
        console.log(this.receiverForm.object)
        if(valid) {
          this.receiverVisible=false;
          this.$message.success('保存成功')
        }else{
          this.$message.warning('接收人信息格式有误')
        }
      })
    },
    //
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    goBack()
    {
      this.$router.go(-1);
    },
    receiversRegionChange(data,index)
    {
      var temp;
      if(data.province.value) {
        temp=data.province.value;
      }
      if(data.city.value) {
        temp=data.province.value+data.city.value;
      }
      if(data.area.value) {
        temp=data.province.value+data.city.value+data.area.value;
      }
      if(data.town.value) {
        temp=data.province.value+data.city.value+data.area.value+data.town.value;
      }

      if(index>=this.receiversRegionData.length)
        this.receiversRegionData.push(temp)
      else
        this.receiversRegionData[index]=temp;
    },
    siteRegionChange (data){
      this.site_options=[];
      this.site_value="";
      if(data.province.value) {
        this.siteRegionData=data.province.value;
        console.log(this.siteRegionData);
      }
      if(data.city.value) {
        this.siteRegionData=data.province.value+data.city.value;
        console.log(this.siteRegionData);
      }
      if(data.area.value) {
        this.siteRegionData=data.province.value+data.city.value+data.area.value;
        console.log(this.siteRegionData);
      }
      if(data.town.value) {
        this.siteRegionData=data.province.value+data.city.value+data.area.value+data.town.value;
        request.get("http://localhost:9090/site/getSitesByRegion",{params:{region:this.siteRegionData}}).then(res=> {
          for(var key in res){
            console.info(key+":"+res[key]);

            this.site_options.push({label:res[key].siteName,value:res[key].siteId});
          };
        });
      }
    },
    senderRegionChange (data) {
      if(data.province.value) {
        this.senderRegionData=data.province.value;
      }
      if(data.city.value) {
        this.senderRegionData=data.province.value+data.city.value;
      }
      if(data.area.value) {
        this.senderRegionData=data.province.value+data.city.value+data.area.value;
      }
      if(data.town.value) {
        this.senderRegionData=data.province.value+data.city.value+data.area.value+data.town.value;
      }
    },
    receiverRegionChange (data) {
      if(data.province.value) {
        this.receiverRegionData=data.province.value;
      }
      if(data.city.value) {
        this.receiverRegionData=data.province.value+data.city.value;
      }
      if(data.area.value) {
        this.receiverRegionData=data.province.value+data.city.value+data.area.value;
      }
      if(data.town.value) {
        this.receiverRegionData=data.province.value+data.city.value+data.area.value+data.town.value;
      }
    },
    onSubmit(){
      if(this.site_value=="")
      {
        this.$message({
          type: 'warning',
          message: '快递点未选择'
        });
      }
      else
      {
      this.$refs["FormRef"].validate(valid => {
        if(valid)
        {
          if(this.value=='single')
          {
            this.formData.origin=this.senderRegionData+this.formData.origin;
            this.formData.dest=this.receiverRegionData+this.formData.dest;
            this.formData.currentSite=Number(this.site_value);
            console.log(this.userdata);
            this.formData.senderUid=Number(this.userdata.userid);
            request.post("http://localhost:9090/order/newOrUpdateOrder", this.formData).then(res => {
              if (res) {
                this.reset();
              }
            })}
          else if(this.value=='many')
          {
              for(var k in this.receiverForm.object)
              {
                this.formData.origin=this.senderRegionData+this.formData.origin;
                this.formData.dest=this.receiversRegionData[k]+this.receiverForm.object[k].address;
                this.formData.receiverName=this.receiverForm.object[k].name;
                this.formData.receiverPhone=this.receiverForm.object[k].phone;
                this.formData.currentSite=Number(this.site_value);
                console.log(this.userdata);
                this.formData.senderUid=Number(this.userdata.userid);
                console.log(this.formData);
                request.post("http://localhost:9090/order/newOrUpdateOrder", this.formData).then(res => {
                  if (res) {
                    console.log("success");
                  }
                })}
              }
          this.reset();
        }
        else
        {
          this.$message({
            type: 'warning',
            message: '请按照指定提示填充信息'
          });
        }
      })
      }
    },
    reset()
    {
      this.formData="";
      this.$router.replace("/UserOrder");
      this.$message({
        type: 'success',
        message: '预约发货成功'
      });
    }
  }
}
</script>

<style lang="less" scoped>
.container1 {
  height: 100%;
}

.el-header {
  padding: 0px;
}


.el-main {
  background-image: linear-gradient(0deg, rgb(245, 247, 250, 0.1) 0%, rgba(195, 207, 226, 0.1) 70%), linear-gradient(-225deg, #5271C4 0%, #B19FFF 48%, #ECA1FE 100%);
  height: 100%;
}

.colPageHeader {
  height: 40px;
}

.leftContainer {
  position: relative;
  top: 10%;
  left: 2.5%;
  background:transparent;
  border-radius: 30px;
  width: 95%;
  height: 650px;
}
.rightContainer {
  position: relative;
  top: 3%;
  left: 2.5%;
  background-color: #ffffff;
  border-radius: 30px;
  width: 95%;
  height: 85%;
}
.orderTable {
  position: relative;
  left: 3%;
  top: 10%;
  width: 94%;
}
.partitionBlock
{
  position: relative;
  left: 3%;
  top:70%
}
.logo
{
  position: relative;
  height: 250px;
  width: 260px;
  text-align: center;
  overflow: hidden;
}
.logoimg
{
  width: 250px;
  overflow-x: hidden;
}
.switch
{
  position: relative;
  left: 30%;
}
.options
{
  margin-top: 20px;
  left: 20%;
  width: 60%;
}
.search
{
  margin-top: 20px;
  left: 20%;
}
.search2
{
  margin-top: 20px;
}
.commitForm
{
  position: relative;
  top:8%;
  width: 90%;
}
.searchBox
{
  width: 100%;
}
.regionBox
{
  width: 100%;
}
/deep/ .rg-default-btn
{
  width: 100%;
}
/deep/ .rg-caller-container
{
  width: 100%;
}
</style>