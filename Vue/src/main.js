import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import "../src/assets/global.css"
import router from './router'
import iView from 'iview';
import 'iview/dist/styles/iview.css';

Vue.config.productionTip = false
Vue.use(ElementUI,{size:"medium"});
Vue.use(iView);


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
