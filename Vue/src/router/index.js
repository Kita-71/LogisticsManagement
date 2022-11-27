import Vue from 'vue'
import VueRouter from 'vue-router'
import UserHomeView from '../views/User/UserHomeView.vue'
import SignInUpView from "@/views/SignInUpView";
import UserOrderInfoView from "@/views/User/UserOrderInfoView";
import UserInfoView from "@/views/User/UserInfoView";
import UserJoinView from "@/views/User/UserJoinView";
import UserBookView from "@/views/User/UserBookView";
import UserOrderView from "@/views/User/UserOrderView";

import ManagerHomeView from "@/views/Manager/ManagerHomeView";
import ManagerUserView from "@/views/Manager/ManagerUserView";
import ManagerJoinView from "@/views/Manager/ManagerJoinView";
import ManagerCourierView from "@/views/Manager/ManagerCourierView";
import ManagerOrderView from "@/views/Manager/ManagerOrderView";

import CourierHomeView from "@/views/Courier/CourierHomeView";
import CourierInfoView from "@/views/Courier/CourierInfoView";
import CourierOrderView from "@/views/Courier/CourierOrderView";
import CourierOperView from "@/views/Courier/CourierOperView";
import CourierGoodsView from "@/views/Courier/CourierGoodsView";

Vue.use(VueRouter)

const routes = [
    {
        path: '/Sign',
        name: 'Sign' ,
        component: SignInUpView,
    },
    {
        path: '/UserHome',
        name: 'UserHome' ,
        component: UserHomeView,
        meta: {requireAuth:true,requireUserAuth:true}
    },
    {
        path: '/UserOrderInfo',
        name: 'UserOrderInfo' ,
        component: UserOrderInfoView,
        meta: {requireAuth:true,requireUserAuth:true}
    },
    {
        path: '/UserInfo',
        name: 'UserInfo' ,
        component: UserInfoView,
        meta: {requireAuth:true,requireUserAuth:true}
    },
    {
        path: '/UserJoin',
        name: 'UserJoin' ,
        component: UserJoinView,
        meta: {requireAuth:true,requireUserAuth:true}
    },
    {
        path: '/UserBook',
        name: 'UserBook' ,
        component: UserBookView,
        meta: {requireAuth:true,requireUserAuth:true}
    },
    {
        path: '/UserOrder',
        name: 'UserOrder' ,
        component: UserOrderView,
        meta: {requireAuth:true,requireUserAuth:true}
    },
    {
        path: '/ManagerUser',
        name: 'ManagerUser' ,
        component: ManagerUserView,
        meta: {requireAuth:true,requireAdminAuth:true}
    },
    {
        path: '/ManagerHome',
        name: 'ManagerHome' ,
        component: ManagerHomeView,
        meta: {requireAuth:true,requireAdminAuth:true}
    },
    {
        path: '/ManagerJoin',
        name: 'ManagerJoin' ,
        component: ManagerJoinView,
        meta: {requireAuth:true,requireAdminAuth:true}
    },
    {
        path:'/ManagerCourier',
        name: 'ManagerCourier',
        component: ManagerCourierView,
        meta: {requireAuth:true,requireAdminAuth:true}
    },
    {
        path:'/ManagerOrder',
        name: 'ManagerOrder',
        component: ManagerOrderView,
        meta: {requireAuth:true,requireAdminAuth:true}
    },
    {
        path:'/CourierHome',
        name: 'CourierHome' ,
        component: CourierHomeView,
        meta: {requireAuth:true,requireCourierAuth:true}
    },
    {
        path:'/CourierOrder',
        name: 'CourierOrder' ,
        component: CourierOrderView,
        meta: {requireAuth:true,requireCourierAuth:true}
    },
    {
        path:'/CourierInfo',
        name: 'CourierInfo' ,
        component: CourierInfoView,
        meta: {requireAuth:true,requireCourierAuth:true}
    },
    {
        path:'/CourierOper',
        name: 'CourierOper' ,
        component: CourierOperView,
        meta: {requireAuth:true,requireCourierAuth:true}
    },
    {
        path:'/CourierGoods',
        name: 'CourierGoods' ,
        component: CourierGoodsView,
        meta: {requireAuth:true,requireCourierAuth:true}
    },
]

const createRouter = () => new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
const router = createRouter();

export default router
