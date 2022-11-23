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
          <el-page-header class="pageheader" @back="goBack" content="订单管理">
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
                  <el-switch
                      class="switch"
                      style="display: block"
                      v-model="orderMode"
                      active-color="#13ce66"
                      inactive-color="#ff4949"
                      active-text="寄"
                      inactive-text="收">
                  </el-switch>
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
              </el-col>
              <el-col  :span="20" style="height: 100%">
                <el-row>
                  <el-col :span="6">
                    <el-select v-model="search_value" placeholder="请选择" class="search">
                      <el-option
                          v-for="item in search_options"
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
                        class="search2">
                      <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                    </el-input>
                  </el-col>
                </el-row>
                <el-table
                    class="orderTable">
                  <el-table-column
                      label="订单号"
                      prop="order_id">
                  </el-table-column>
                  <el-table-column
                      label="始发地"
                      prop="origin">
                  </el-table-column>
                  <el-table-column
                      label="收货地址"
                      prop="dest">
                  </el-table-column>
                  <el-table-column
                      label="货物"
                      prop="goods">
                  </el-table-column>
                </el-table>
                <el-pagination
                    class="partitionBlock"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage4"
                    :page-sizes="[9, 18, 27, 36]"
                    :page-size="5"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
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

export default {
  components: {UserHeader},
  name: "UserOrderView",
  data() {
    return {
      //状态选择框
      options: [{
        value: '全部订单',
        label: '全部订单'
      },
        {
          value: '待寄出',
          label: '待寄出'
      },
        {
        value: '运输中',
        label: '运输中'
      },
        {
        value: '派送中',
        label: '派送中'
      }, {
        value: '已签收',
        label: '已签收'
      }],
      value: '全部订单',
      //搜索选项

      search_options: [{
        value: '用户名',
        label: '用户名'
      }, {
        value: '手机号',
        label: '手机号'
      }, {
        value: '邮箱',
        label: '邮箱'
      }],
      search_value:"用户名",
      tableData: [],
      total: 0,
      orderMode:'1',
      search_input:''
    }
  },
  created(){
    fetch("http://localhost:9090/UserOrder/pageget?pageNum=1&pageSize=2").then(res => res.json()).then(res => {
      console.log(res)
      this.tableData=res.data
      this.total=res.total
    })
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
    }
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