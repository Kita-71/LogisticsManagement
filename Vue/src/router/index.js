import Vue from 'vue'
import VueRouter from 'vue-router'
import UserHomeView from '../views/User/UserHomeView.vue'
import SignInUpView from "@/views/SignInUpView";
import UserOrderInfoView from "@/views/User/UserOrderInfoView";
import UserInfoView from "@/views/User/UserInfoView";
import UserChangeInfoView from "@/views/User/UserChangeInfoView";
import UserJoinView from "@/views/User/UserJoinView";
import UserBookView from "@/views/User/UserBookView";
import UserOrderView from "@/views/User/UserOrderView";
import ManagerHomeView from "@/views/Manager/ManagerHomeView";
import ManagerUserView from "@/views/Manager/ManagerUserView";
import ManagerJoinView from "@/views/Manager/ManagerJoinView";
import ManagerCourierView from "@/views/Manager/ManagerCourierView";
import ManagerOrderView from "@/views/Manager/ManagerOrderView";
import CourierHelloView from "@/views/Courier/CourierHelloView";
import CourierHomeView from "@/views/Courier/CourierHomeView";
import CourierInfoView from "@/views/Courier/CourierInfoView";
import CourierOrderView from "@/views/Courier/CourierOrderView";
import CourierOperView from "@/views/Courier/CourierOperView";
import CourierGoodsView from "@/views/Courier/CourierGoodsView";

Vue.use(VueRouter)

const routes = [
    {
        path: '/UserOrderInfo',
        name: 'UserOrderInfo' ,
        component: UserOrderInfoView,
        meta: {requireAuth:true}
    },
    {
        path: '/UserHome',
        name: 'UserHome' ,
        component: UserHomeView,
        meta: {requireAuth:true}
    },
    {
        path: '/UserInfo',
        name: 'UserInfo' ,
        component: UserInfoView,
        meta: {requireAuth:true}
    },
    {
        path: '/UserChangeInfo',
        name: 'UserChangeInfo' ,
        component: UserChangeInfoView,
        meta: {requireAuth:true}
    },
    {
        path: '/UserJoin',
        name: 'UserJoin' ,
        component: UserJoinView,
        meta: {requireAuth:true}
    },
    {
        path: '/Sign',
        name: 'Sign' ,
        component: SignInUpView,
    },
    {
        path: '/UserBook',
        name: 'UserBook' ,
        component: UserBookView,
        meta: {requireAuth:true}
    },
    {
        path: '/UserOrder',
        name: 'UserOrder' ,
        component: UserOrderView,
        meta: {requireAuth:true}
    },
    {
        path: '/ManagerUser',
        name: 'ManagerUser' ,
        component: ManagerUserView
    },
    {
        path: '/ManagerHome',
        name: 'ManagerHome' ,
        component: ManagerHomeView
    },
    {
        path: '/ywchhhhhh',
        name: 'order' ,
        component: UserOrderView
    },
    {
        path: '/CourierHello',
        name: 'CourierHello' ,
        component: CourierHelloView
    },
    {
        path: '/ManagerJoin',
        name: 'ManagerJoin' ,
        component: ManagerJoinView
    },
    {
        path:'/CourierHome',
        name: 'CourierHome' ,
        component: CourierHomeView
    },
    {
        path:'/CourierOrder',
        name: 'CourierOrder' ,
        component: CourierOrderView
    },
    {
        path:'/CourierInfo',
        name: 'CourierInfo' ,
        component: CourierInfoView
    },
    {
        path:'/CourierOper',
        name: 'CourierOper' ,
        component: CourierOperView
    },
    {
        path:'/CourierGoods',
        name: 'CourierGoods' ,
        component: CourierGoodsView
    }
]

const createRouter = () => new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
const router = createRouter();

export default router
