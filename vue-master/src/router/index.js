import Vue from 'vue'
import Router from 'vue-router'
import Login  from '@/components/Login'
import Home from '@/components/Home'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    },
    {
      path: '/Home',
      name: 'Home',
      component: Home,
      hidden: true
    }
  ]
})
