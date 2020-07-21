import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import Signup from '../page/user/Signup.vue'
import Authentication from '../page/user/Authentication'
import Detail from '../page/user/Detail'
import Update from '../page/user/Update'
import Delete from '../page/user/Delete'

// 포스트
import List from '../page/post/List.vue'
 
Vue.use(Router) 
 
export default new Router({
  routes: [   
    // 포스트
    { 
      path: '/',
      name: constants.URL_TYPE.POST.MAIN,
      component: List,
    },
    // 로그인/가입
    { 
      path: '/user/login',
      name: constants.URL_TYPE.USER.LOGIN,
      component: Login
    },
    {
      path: '/user/join',
      name: constants.URL_TYPE.USER.JOIN,
      component: Join
    },
    {
      path: '/user/signup',
      name:constants.URL_TYPE.USER.SIGNUP,
      component: Signup
    },
    {
      path: '/user/auth',
      name:constants.URL_TYPE.USER.AUTH,
      component: Authentication
    },
    {
      path: '/user/detail',
      name:constants.URL_TYPE.USER.DETAIL,
      component: Detail
    },
    {
      path: '/user/update',
      name:constants.URL_TYPE.USER.UPDATE,
      component: Update
    },
    {
      path: '/user/delete',
      name:constants.URL_TYPE.USER.DELETE,
      component: Delete
    }
    
  ]
})
