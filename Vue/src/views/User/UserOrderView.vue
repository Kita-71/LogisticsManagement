<template>
  <el-container class="container1">
    <!-- 顶栏 -->
    <el-header height="60px">
      <UserHeader></UserHeader>
    </el-header>
    <el-main>
      <!-- 第一列布局 -->
      <el-row>
        <el-col :span="24" class="colPageHeader">
          <el-page-header class="pageheader" @back="goBack" content="订单管理">
          </el-page-header>
        </el-col>
      </el-row>
      <!-- 第二列栅格布局 -->
      <el-row :gutter="20">
        <el-col class="colSelect" :span="4">
          <div class="leftContainer">

          </div>
        </el-col>
        <el-col class="colTable" :span="20">
          <div class="rightContainer">
            <el-table
                class="orderTable"
                :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))">
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
              <el-table-column
                  align="right">
                <template slot="header" slot-scope="scope">
                  <el-input
                      v-model="search"
                      size="mini"
                      placeholder="输入关键字搜索"/>
                </template>
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">详细
                  </el-button>
                  <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">修改
                  </el-button>
                  <el-button
                      size="mini"
                      type="danger"
                      @click="handleDelete(scope.$index, scope.row)">删除
                  </el-button>
                </template>
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
      // tableData: [{
      //   date: 1,
      //   name: 'Go',
      //   address: '上海市普陀区金沙江路 1518 '
      // }, {
      //   date: '2016-05-05',
      //   name: '王小虎',
      //   address: '上海市普陀区金沙江路 1517 弄'
      // }, {
      //   date: '2016-05-01',
      //   name: '王小虎',
      //   address: '上海市普陀区金沙江路 1519 弄'
      // }, {
      //   date: '2016-05-03',
      //   name: '王小虎',
      //   address: '上海市普陀区金沙江路 1516 弄'
      // }],
      tableData: [],
      search: '',
      total: 0
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
  background-image: linear-gradient(-225deg, rgba(255, 255, 255, 0.5) 0%, rgba(255, 255, 255, 0.5) 100%);
  border-radius: 30px;
  width: 95%;
  height: 650px;
}
.rightContainer {
  position: relative;
  top: 10%;
  left: 2.5%;
  background-color: #ffffff;
  border-radius: 30px;
  width: 95%;
  height: 650px;
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
  top:40%
}

</style>