<template>
  <div id="header" v-if="isHeader">
    <h1>
      <router-link v-bind:to="{ name: constants.URL_TYPE.POST.MAIN }">
        SS_log
      </router-link>
    </h1>

    <div class="right">
      <h6 v-if="loginStatus">
        <strong>{{ nickname }}</strong
        >님 환영합니다.
        <router-link v-bind:to="{ name: constants.URL_TYPE.POST.SEARCH }">
          <v-btn rounded class="ma-2" color="secondary" icon dark>
            <v-icon>mdi-magnify</v-icon>검색하기
          </v-btn>
        </router-link>

        <button class="btn" v-on:click="logout">
          로그아웃
        </button>
        <button class="btn" v-on:click="moveDetail">
          회원정보
        </button>
        <button class="btn" v-on:click="moveSearch">
          검색하기
        </button>
      </h6>

      <router-link
        v-if="!loginStatus"
        v-bind:to="{ name: constants.URL_TYPE.USER.LOGIN }"
        class="login-btn"
      >
        로그인
      </router-link>
    </div>
  </div>
</template>

<script>
import constants from "../../lib/constants";

const storage = window.sessionStorage;
console.log(storage);
export default {
  name: "Header",
  components: {},
  props: ["isHeader"],
  computed: {},
  watch: {},
  created() {},
  mounted() {
    this.init();
  },
  updated() {
    this.init();
  },
  methods: {
    init() {
      if (storage.getItem("jwt-auth-token")) {
        this.nickname = storage.getItem("login_user");
        this.loginStatus = true;
      } else {
        storage.setItem("jwt-auth-token", "");
        this.nickname = "";
        this.loginStatus = false;
      }
    },
    logout() {
      storage.setItem("jwt-auth-token", "");
      storage.setItem("login_user", "");
      this.init();
    },
    moveDetail() {
      this.$router.push("/user/detail");
    },
    moveSearch(){
      this.$router.push("/search");
    }
  },
  data: function() {
    return {
      constants,
      keyword: "",
      nickname: "",
      password: "",
      email: "",
      loginStatus: false,
    };
  },
};
</script>
