<template>
  <div class="usercheck">
    <UserHeader></UserHeader>
        <el-main>
          <img class="img1" src="../assets/Welcome.png"></img>
          <el-input
              placeholder="请输入内容"
              suffix-icon="el-icon-date"
              v-model="search_input">
          </el-input>
        </el-main>
  </div>
</template>
<style lang="less" scoped>
.usercheck
{
    height: 100%;width:100%;border: 1px solid #eee;
    background-image: linear-gradient(0deg, 	rgb(245,247,250,0.4) 0%, 	rgba(195,207,226,0.1) 80%),linear-gradient(-225deg, #5271C4 0%, #B19FFF 48%, #ECA1FE 100%);;
    background-size:100% 100%;
    background-repeat: no-repeat;
    .el-main
    {

      margin:0px;
      padding-right: 0;
      padding-left: 0;
      .img1{
        position: absolute;
        left: 50%;
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

</style>

<script>
import UserHeader from "@/components/User/UserHeader";
export default {
  components: {UserHeader},
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(15).fill(item),
      isCollapse:false,
      sideWidth:200,
      options: [{
        value: '选项1',
        label: '站点ID'
      }, {
        value: '选项2',
        label: '站点名称'
      }, {
        value: '选项3',
        label: '站点地址'
      }],
      search_input: ''
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
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>