import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import "../src/assets/global.css"
import router from './router'
import store from './store'
import request from './utils/request'
import Plugin from 'v-fit-columns'
import Region from 'v-region'

Vue.use(Region)
Vue.config.productionTip = false
Vue.use(ElementUI,{size:"medium"});
Vue.use(Plugin);
Vue.prototype.request=request;
router.beforeEach((to, from, next) => {
      if (to.meta.requireAuth) {
          if(to.meta.requireUserAuth)
          {
              if (store.state.user.username) {
                  next()
              } else {
                  next({
                      path: '/Sign',
                      query: {redirect: to.fullPath,access:"commonUser"}
                  })
              }
          }
          else if(to.meta.requireCourierAuth)
          {
              if (store.state.courier.username) {
                  next()
              } else {
                  next({
                      path: '/Sign',
                      query: {redirect: to.fullPath,access:"deliveryStaff"}
                  })
              }
          }
          else if(to.meta.requireAdminAuth)
          {
              if (store.state.admin.username) {
                  next()
              } else {
                  next({
                      path: '/Sign',
                      query: {redirect: to.fullPath,access:"admin"}
                  })
              }
          }
      }
      else {
        next()
      }
    }
)
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
