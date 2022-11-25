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
    },
    mutations: {
        login (state, user) {
            state.user = user
            window.localStorage.setItem('user', JSON.stringify(user))
        },
        exit(state)
        {
          state.user="";
          window.localStorage.clear();
        }
    }
})
export default store