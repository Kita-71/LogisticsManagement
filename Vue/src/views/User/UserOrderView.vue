<template>
  <el-container class="container1">
    <!-- 顶栏 -->
    <el-header height="60px">
      <UserHeader ref="header"></UserHeader>
    </el-header>
    <el-main>
      <!-- 第一行布局 -->
      <el-row style="height: 5%">
        <el-col :span="24" class="colPageHeader">
          <el-page-header class="pageheader" @back="goBack" content="我的订单">
          </el-page-header>
        </el-col>
      </el-row>
      <!-- 第二行栅格布局 -->
      <el-row style="height: 95%;width:80%;left: 10%">
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
                      inactive-text="收"
                      @change="this.getDataTotal">
                  </el-switch>
                </el-row>
                <el-row>
                  <el-select class="options" v-model="value" placeholder="请选择" @change="this.getDataTotal">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                        >
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
                        placeholder= "请输入发出地中包含的内容，如”深圳“"
                        v-model="search_input"
                        class="search2"
                        v-if="this.search_value==='origin'">
                      <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                    </el-input>
                      <el-input
                          placeholder="请输入目的地中包含的内容，如”深圳“"
                          v-model="search_input"
                          class="search2" v-if="this.search_value==='dest'">
                        <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                      </el-input>
                        <el-input
                            placeholder="请输入订单创建日期，直接输入年月日，如20221105"
                            v-model="search_input"
                            class="search2" v-if="this.search_value==='time'">
                          <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
                        </el-input>
                  </el-col>
                </el-row>
                <el-row style="height: 80%">
                <el-table
                    class="orderTable"
                    :data="orderData"
                    height="100%"
                    border
                >
                  <el-table-column
                      label="订单号"
                      prop="orderId"
                      width="270px">
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

                  <el-table-column
                      prop="state"
                      label="状态"
                      width="160">
                    <template slot-scope="scope">
                      <el-tag v-if="scope.row.state=='reserve'"
                              :type="'primary'"
                              disable-transitions>待发出</el-tag>
                      <el-tag v-else-if="scope.row.state=='in_transport'"
                              :type="'info'"
                              disable-transitions>运输中</el-tag>
                      <el-tag v-else-if="scope.row.state=='pending_pickup'"
                              :type="'warning'"
                              disable-transitions>待派送</el-tag>
                      <el-tag v-else-if="scope.row.state=='done'"
                              :type="'success'"
                              disable-transitions>已完成</el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column>
                    <template slot-scope="scope">
                      <el-button
                          size="mini"
                          @click="handleLook(scope.$index, scope.row)">详细
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
                </el-row>
                <el-row  style="height: 10%">
                <el-pagination
                    class="partitionBlock"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage4"
                    :page-sizes="[9, 18, 27, 36]"
                    :page-size= "page_size"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
                </el-row>
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
import request from "@/utils/request";

export default {
  components: {UserHeader},
  name: "UserOrderView",
  data() {
    return {
      //状态选择框
      orderData:[],
      options: [{
        value: "all",
        label: '全部订单'
      },
        {
          value: "reserve",
          label: '待寄出'
      },
        {
        value: "in_transport",
        label: '运输中'
      },
        {
        value: "pending_pickup",
        label: '派送中'
      }, {
        value: "done",
        label: '已签收'
      }],
      value: "all", //显示的选项

      search_options: [{
        value: 'origin',
        label: '始发地'
      }, {
        value: 'dest',
        label: '目的地'
      }, {
        value: 'time',
        label: '订单创建时间'
      }],
      tableData: [],
      total: 0,
      orderMode: false,//和[寄/收]的按钮有关，
      search_value:"origin",//搜索的内容
      search_input:'',//搜索框的内容
      page_size:9,
      currentPage4: 1,
    }
  },
  created(){
    this.getDataTotal();
  },
  methods: {
    handleLook(index, row)
    {
      request.get("http://localhost:9090/order/getorder",{params:{orderId:row.orderId}}).then(res=>{
        if(res)
        {
          this.$router.push({
            name:"UserOrderInfo",//这个name就是你刚刚配置在router里边的name
            query:{
              orderId:row.orderId
            }})
        }
      })
    },
    getDataTotal()
    {
      this.request.get("http://localhost:9090/user/get",{params:{username:this.$store.state.user.username}})
          .then(res=>
          {
            var userdata=res;
            console.log(this.currentPage4);
            console.log(this.page_size);
            request.get("http://localhost:9090/order/pageGetByPhone",{params:
                  {pageNum:this.currentPage4,pageSize:this.page_size,phone:userdata.phone,orderMode:this.orderMode,state:this.value,searchMode:this.search_value,search_input:this.search_input,receiver_uid:userdata.userid,sender_uid:userdata.userid}})
                .then(res=>{
                  this.orderData=res.records;
                  this.total=res.total;
                });
          })

    },
    handleSizeChange(val){
      this.page_size=val;
      this.getDataTotal();
      },
    handleCurrentChange(val){
      this.currentPage4=val;
      this.getDataTotal();
    },
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

    //搜索按钮点击事件，在此
    search()
    {
      this.getDataTotal();
      this.$message({
        type: 'success',
        message: '搜索成功'
      });
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
  height: 70%;
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
  height: 95%;
}
.orderTable {
  position: relative;
  left: 3%;
  top: 5%;
  width: 94%;
}
.partitionBlock
{
  position: relative;
  top:50%;
  left:30%;
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
  width: 150%;
}
</style>