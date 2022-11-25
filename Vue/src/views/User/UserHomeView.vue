<template>
  <div class="userCheck">
    <UserHeader></UserHeader>
        <el-main>
          <img class="img1" src="../../assets/BabyQ.png"></img>
          <el-input
              placeholder="请输入要查询的物流订单"
              v-model="search_input">
            <el-button slot="append" icon="el-icon-search" @click="searchOrder"></el-button>
          </el-input>
          <UserScroll class="scroll"></UserScroll>
        </el-main>
  </div>
</template>
<style lang="less" scoped>
.userCheck
{
    height: 100%;
    width:100%;
    border: 1px solid #eee;
    background-size:100% 100%;
    background-repeat: no-repeat;
    .el-main
    {
      background-image:
          linear-gradient(0deg, 	rgb(245,247,250,0.1) 0%, 	rgba(195,207,226,0.1) 70%),
          linear-gradient(-225deg, #5271C4 0%, #B19FFF 48%, #ECA1FE 100%);
      height: 100%; 
      margin:0px;
      padding-right: 0;
      padding-left: 0;

      .img1{
        position: absolute;
        left:54%;
        top:25%;
        width: 600px;
        transform: translate(-50%,-50%);
        font-family: "Arial";
      }
      .el-input
      {
        position: absolute;
        left:50%;
        top:40%;
        width: 700px;
        transform: translate(-50%,-50%);

      }
    }
  }
  .scroll
  {
    position: relative;
    top: 90%;
  }
</style>

<script>
import UserHeader from "@/components/User/UserHeader";
import UserScroll from "@/components/User/UserScroll";
import request from "@/utils/request";
export default {
  components: {UserHeader,UserScroll},
  data() {
    return {
      search_input: '',
    }
  },
  methods:{
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    //搜索接口 搜索成功后跳转到订单页
    searchOrder()
    {
      request.get("http://localhost:9090/order/getorder",{params:{orderId:this.search_input}}).then(res=>{
        if(res)
        {
          this.$message({
            type: 'success',
            message: '搜索成功'
          });
          this.$router.push({
            name:"UserOrderInfo",//这个name就是你刚刚配置在router里边的name
            query:{
              orderId:this.search_input
            }})
        }
        else
        {
          this.$message({
            type: 'info',
            message: '该订单不存在'
          });
        }
      })
    }
  }
}
</script>