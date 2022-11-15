import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/UserCheckView.vue'
import LoginView from "@/views/SignInUpView";
import SignInUpView from "@/views/SignInUpView";
import UserCheckView from "@/views/UserCheckView";

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
component: UserCheckView
}
]

const router = new VueRouter({
mode: 'history',
base: process.env.BASE_URL,
routes
})

export default router
