<template>
  <el-container class="containerAll">
    <el-header >
      <CourierHeader></CourierHeader>
    </el-header>
    <el-container class="containerBottom">
      <el-aside class="aside" width="260px" >
        <CourierAside></CourierAside>
      </el-aside>
      <el-main>
        <div class="block">
          <el-image :src="require('@/assets/main.jpg')" alt=""  class="img">          </el-image>
        </div>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CourierAside from "@/components/Courier/CourierAside";
import CourierHeader from "@/components/Courier/CourierHeader";
export default {
  name: "CourierHomeView",
  components:{CourierAside,CourierHeader},
  data() {
    return {
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
.block

{ width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  .img
  {

    max-width: 100%;
    max-height: 700px;
    width: 500%;
    height: 500%;
  }
}
</style>