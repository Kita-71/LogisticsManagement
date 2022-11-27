import Vue from 'vue'
import Vuex from 'vuex'
import router from "@/router";

Vue.use(Vuex)
const store= new Vuex.Store({
    state: {
        //构建一个用户对象
        user: {
            username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
        },
        //构建一个快递店员工
        courier: {
            username: window.localStorage.getItem('courier' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('courier' || '[]')).username
        },
        //构建一个管理员员工
        admin: {
            username: window.localStorage.getItem('admin' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('admin' || '[]')).username
        },
    },
    mutations: {
        user_login (state, user) {
            state.user = user
            window.localStorage.setItem('user', JSON.stringify(user))
        },
        courier_login (state, courier) {
            state.courier = courier
            window.localStorage.setItem('courier', JSON.stringify(courier))
        },
        admin_login (state, admin) {
            state.admin = admin
            window.localStorage.setItem('admin', JSON.stringify(admin))
        },
        user_exit(state)
        {
          state.user="";
          window.localStorage.removeItem('user');
        },
        courier_exit(state)
        {
            state.courier="";
            window.localStorage.removeItem('courier');
        },
        admin_exit(state)
        {
            state.admin="";
            window.localStorage.removeItem('admin');
        }
    }
})
export default store