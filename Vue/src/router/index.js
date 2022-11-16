import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/User/UserCheckView.vue'
import LoginView from "@/views/SignInUpView";
import SignInUpView from "@/views/SignInUpView";
import UserCheckView from "@/views/User/UserCheckView";
import UserOrderInfoView from "@/views/User/UserOrderInfoView";
import UserInfoView from "@/views/User/UserInfoView";
import UserChangeInfoView from "@/views/User/UserChangeInfoView";
import UserJoinView from "@/views/User/UserJoinView";

Vue.use(VueRouter)

const routes = [
    {
    path: '/sign',
    name: 'signinup' ,
    component: SignInUpView
    },
    {
    path: '/',
    name: 'Manager' ,
    component: UserOrderInfoView
    },
    {
        path: '/check',
        name: 'Manager' ,
        component: UserCheckView
    },
    {
        path: '/info',
        name: 'Manager' ,
        component: UserInfoView
    },
    {
        path: '/info',
        name: 'Manager' ,
        component: UserInfoView
    },
    {
        path: '/changeinfo',
        name: 'Manager' ,
        component: UserChangeInfoView
    },
    {
        path: '/join',
        name: 'Manager' ,
        component: UserJoinView
    }
]

const router = new VueRouter({
mode: 'history',
base: process.env.BASE_URL,
routes
})

export default router
