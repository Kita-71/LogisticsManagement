import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import "../src/assets/global.css"
import router from './router'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import store from './store'
import axios from 'axios';

Vue.config.productionTip = false
Vue.use(ElementUI,{size:"medium"});
Vue.use(iView);

router.beforeEach((to, from, next) => {
      if (to.meta.requireAuth) {
        if (store.state.user.username) {
          next()
        } else {
          next({
            path: '/',
            query: {redirect: to.fullPath}
          })
        }
      } else {
        next()
      }
    }
)
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
Vue.prototype.$axios = axios;
