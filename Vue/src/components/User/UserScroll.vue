<template>
  <div>
    <div class="textBox">
      <transition name="slide">
        <p class="text" :key="text.id">
          <el-tag type="warning">{{text.val.title}}</el-tag>
          {{text.val.description}}</p>
      </transition>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'scroll',
  created() {
    request.get("http://localhost:9090/info/all").then(res=>
    {
        this.textArr=res;
    })
  },
  data () {
    return {
      textArr: [
        {},
      ],
      number: 0
    }
  },
  computed: {
    text () {
      return {
        id: this.number,
        val: this.textArr[this.number]
      }
    }
  },
  mounted () {
    this.startMove()
  },
  methods: {
    startMove () {
      // eslint-disable-next-line
      let timer = setTimeout(() => {
        if (this.number === this.textArr.length) {
          this.number = 0;
        } else {
          this.number += 1;
        }
        this.startMove();
      }, 2500); // 滚动不需要停顿则将2000改成动画持续时间
    }
  }
}
</script>

<style scoped>
.textBox {
  width: 100%;
  height: 40px;
  margin: 0 auto;
  overflow: hidden;
  position: relative;
  text-align: center;
}
.text {
  width: 100%;
  position: absolute;
  bottom: 0;
}
.slide-enter-active, .slide-leave-active {
  transition: all 0.5s linear;
}
.slide-enter{
  transform: translateY(20px) scale(1);
  opacity: 1;
}
.slide-leave-to {
  transform: translateY(-20px) scale(0.8);
  opacity: 0;
}
</style>