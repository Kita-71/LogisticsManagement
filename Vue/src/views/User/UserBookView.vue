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

                <el-form ref="form" :model="form"  class="commitForm">
                  <el-form-item label="站点名称" :required="true">
                    <el-input v-model="form.name"></el-input>
                  </el-form-item>
                  <el-form-item label="站点所在区域" :required="true">
                    <region-group
                        separator="-"
                        :town="true"
                        v-model="region"
                        @change="regionChange"
                    />
                  </el-form-item>
                  <el-form-item label="站点详细地址" :required="true">
                    <el-input v-model="form.address" placeholder="如:xx村/xx路/xx门牌号"></el-input>
                  </el-form-item>
                  <el-form-item label="申请人真实姓名" :required="true">
                    <el-input v-model="form.realName"></el-input>
                  </el-form-item>
                  <el-form-item label="申请人身份证号" :required="true">
                    <el-input v-model="form.id"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit">立即创建</el-button>
                    <el-button>取消</el-button>
                  </el-form-item>
                </el-form>
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

export default {
  components: {UserHeader ,RegionGroup},
  name: "UserOrderView",
  data() {
    return {
      //状态选择框
      options: [{
        value:'普通寄件',
        label: '普通寄件'
      },
        {
          value: '批量寄件',
          label: '批量寄件'
        }],
      region: {
      },
      form: {
        name: '',
        region: '',
        address: '',
        type:'',
        realName:'',
        id:'',
      },
      value: '普通寄件',
      tableData: [],
      total: 0,
      orderMode:'1',
      search_input:''
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
    regionChange (data) {
      console.log(data.province.value)
      console.log(data.city.value)
      console.log(data.area.value)
      console.log(data.town.value)
    },
    onSubmit(){},
  },
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
</style>