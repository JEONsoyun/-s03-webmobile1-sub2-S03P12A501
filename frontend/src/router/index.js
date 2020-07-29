import Vue from "vue";
import VueRouter from "vue-router";

import constants from "../lib/constants";

// 유저
import Loginvutify from "../page/user/Loginvuetify.vue";
import Joinvuetify from "../page/user/Joinvuetify.vue";
import Login from "../page/user/Login.vue";
import Join from "../page/user/Join.vue";
import Signup from "../page/user/Signup.vue";
import Authentication from "../page/user/Authentication";
import Detail from "../page/user/Detail";
import Update from "../page/user/Update";
import Delete from "../page/user/Delete";

// 포스트
import List from "../page/post/List.vue";
import Write from "../page/post/Write.vue";
//검색
import Search from "../views/search/Search.vue";
Vue.use(VueRouter);

const routes = [
  // 포스트
  {
    path: "/",
    name: constants.URL_TYPE.POST.MAIN,
    component: List,
  },
  // 로그인/가입
  
  {
    path: "/user/jointest",
    name: constants.URL_TYPE.USER.JOIN,
    component: Joinvuetify,
  },
  {
    path: "/user/logintest",
    name: constants.URL_TYPE.USER.LOGIN,
    component: Loginvutify,
  },
  {
    path: "/user/detail",
    name: constants.URL_TYPE.USER.DETAIL,
    component: Detail,
  },
  {
    path: "/user/signup",
    name: constants.URL_TYPE.USER.SIGNUP,
    component: Signup,
  },
  {
    path: "/user/auth",
    name: constants.URL_TYPE.USER.AUTH,
    component: Authentication,
  },
  {
    path: "/user/delete",
    name: constants.URL_TYPE.USER.DELETE,
    component: Delete,
  },
  /*
    {
      path: "/post/write",
      name: constants.URL_TYPE.POST.LIST,
      component: Write
    }
    */
  //검색
  {
    path: "/search",
    name: constants.URL_TYPE.POST.SEARCH,
    component: Search,
  }
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
})

// router.beforeEach((to, from, next) => {
//   const publicPages = ["Login", "Signup", "List"]; // Login 안해도 됨
//   const authPages = ["Login", "Signup"]; // Login 되어있으면 안됨

//   const authRequired = !publicPages.includes(to.name); // 로그인 해야 함.
//   const unauthRequired = authPages.includes(to.name); // 로그인 해서는 안됨
//   const isLoggedIn = !!Vue.$cookies.isKey("auth-token");

//   if (unauthRequired && isLoggedIn) {
//     next("/");
//   }
//   authRequired && !isLoggedIn ? next({ name: "Login" }) : next();
// });

export default router
