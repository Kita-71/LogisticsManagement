<template>
  <div class="home" style="height: 100%">
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside width="sideWidth+'px'" background-color="#333333">
        <el-menu :default-openeds="['1', '3']"
                 style="min-height:100%;overflow-x:hidden"
                 active-text-color="#f52662"
                 background-color="#333333"
                 text-color="#B3C0D1"
                 :collapse-transition="false"
                 :collapse="isCollapse">
          <div style="height: 60px;color:#FFFFFF;line-height: 40px;text-align: center" >
            <img src ="../assets/babyq.jpg" alt="" style="width:40px;position: relative;top:10px">
            <b style="margin-left: 10px" v-show="!isCollapse">物流管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>
              <span slot="title">导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>
              <span slot="title">导航三</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="font-size: 12px;background-color: #455A64;display: flex;line-height: 60px;">
          <div style="flex: 1;font-size: 18px;width: 30px">
            <span class="el-icon-s-fold" style="cursor: pointer;color: #B3C0D1" @click="collapse"></span>
          </div>
          <el-dropdown >
            <i class="el-icon-setting" style="margin-right: 15px;color: #B3C0D1"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span style="color: #B3C0D1">登录人名称</span>
        </el-header>

        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                :page-sizes="[5, 10, 15, 20]"
                :page-size="10"
                layout="total, sizes, prev, pager, next, jumper"
                :total="400">
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
export default {
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(15).fill(item),
      isCollapse:false,
      sideWidth:200
    }
  },
  methods:{
    collapse(){
      this.isCollapse=!this.isCollapse;
      if(this.isCollapse)
      {
        this.sideWidth=60;
      }
      else
      {
        this.sideWidth=200;
      }
    }
  }
}
</script>
