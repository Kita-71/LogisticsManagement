<template>
  <div>
    <div class="textBox">
      <transition name="slide">
        <p class="text" :key="text.id">
          <el-tag type="warning">{{text.val.tag}}</el-tag>
          {{text.val.title}}</p>
      </transition>
    </div>
  </div>
</template>

<script>
export default {
  name: 'scroll',
  data () {
    return {
      textArr: [
        {tag:'天气影响通知',title:'受2022号台风影响，广东省深圳市的物流服务于2022.11.28日至2022.11.29日暂停一天'},
        {tag:'疫情影响通知',title:'根据疫情防控需求，广东省广州市物流服务暂停，恢复时间另行通知'},
        {tag:'物流优惠活动',title:'双12来袭，12.1-12.12使用本站物流服务可享受九折寄件优惠'}
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