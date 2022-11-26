<template>
  <el-container class="container1">
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
                <div class="commitForm">
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
import router from "@/router";

export default {
  components: {UserHeader ,RegionGroup},
  name: "UserOrderView",
  data() {
    return {
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
      site_value: 'single',
      region: {
      },
      senderRegion:{},
      receiverRegion:{},
      siteRegionData:"",
      senderRegionData:"",
      receiverRegionData:"",
      formData: {
        goods:"",
        origin:"",
        senderName:"",
        senderPhone:"",
        dest:"",
        receiverName:"",
        receiverPhone:"",
        currentSite:"",
        state:"reserve",
        postscript:"",
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
          {required: true, message: '发件人手机号不能为空', trigger: 'blur'}
        ],
        receiverPhone: [
          {required: true, message: '收件人手机号不能为空', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
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
    siteRegionChange (data){
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
        console.log(this.siteRegionData);
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
      this.$refs["FormRef"].validate(valid => {
        this.formData.origin=this.senderRegionData+this.formData.origin;
        this.formData.dest=this.receiverRegionData+this.formData.dest;
        request.post("http://localhost:9090/order/newOrUpdateOrder", this.formData).then(res => {
          if (res) {
            this.reset();
          }
        })
      })
    },
    reset()
    {
      this.formData="";
      this.$router.replace("/UserOrder");
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