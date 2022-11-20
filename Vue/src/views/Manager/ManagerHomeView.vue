<template>
  <el-container class="containerAll">
      <el-header >
        <ManagerHeader></ManagerHeader>
      </el-header>
    <el-container class="containerBottom">
      <el-aside class="aside" width="260px" >
        <ManagerAside></ManagerAside>
      </el-aside>
      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="date" label="日期" width="140">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column prop="address" label="地址">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import ManagerAside from "@/components/Manager/ManagerAside";
import ManagerHeader from "@/components/Manager/ManagerHeader";
export default {
  name: "ManagerHome",
  components:{ManagerAside,ManagerHeader},
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
  padding:0
}
.el-aside
{
  height: 100%;
}
.el-main
{
  height: 100%;
}
</style>