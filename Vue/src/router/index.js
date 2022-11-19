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

Vue.use(VueRouter)

const routes = [
    {
    path: '/UserOrderInfo',
    name: 'UserOrderInfo' ,
    component: UserOrderInfoView,
    },
    {
        path: '/UserHome',
        name: 'UserHome' ,
        component: UserHomeView,
    },
    {
        path: '/UserInfo',
        name: 'UserInfo' ,
        component: UserInfoView,
    },
    {
        path: '/UserChangeInfo',
        name: 'UserChangeInfo' ,
        component: UserChangeInfoView,
    },
    {
        path: '/UserJoin',
        name: 'UserJoin' ,
        component: UserJoinView,
    },
    {
        path: '/',
        name: 'Sign' ,
        component: SignInUpView,
        meta: { isReset: true },
    },
    {
        path: '/UserBook',
        name: 'UserBook' ,
        component: UserBookView,
    },
    {
        path: '/UserOrder',
        name: 'UserOrder' ,
        component: UserOrderView,
    }
]

const createRouter = () => new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
const router = createRouter();

export function resetRouter () {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // the relevant part
}

export default router
