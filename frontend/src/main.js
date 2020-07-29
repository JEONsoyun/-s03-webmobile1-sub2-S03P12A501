import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import store from "./store";
import InfiniteLoading from 'vue-infinite-loading';
/* eslint-disable no-new */
new Vue({
  router,
  store,
  vuetify,
  InfiniteLoading,
  render: (h) => h(App),
}).$mount("#app");
