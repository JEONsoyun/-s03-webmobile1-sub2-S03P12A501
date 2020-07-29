<template>
    <v-app id="inspire">
  <div id="header" >
    <v-app-bar
      absolute
      color="teal lighten-3"
      dark
    >
      <v-btn text to="/"><v-toolbar-title>
        SS_log</v-toolbar-title>
      </v-btn>
      <v-spacer></v-spacer>
      <div v-if="loginStatus">
        <small class="font-weight-bold">{{ nickname }}</small
        >님 환영합니다.
      <v-btn rounded class="ma-2" color="primary" to="/search" >
            <v-icon>mdi-magnify</v-icon>검색하기
          </v-btn>
      <v-btn rounded class="ma-2" color="#9896f1" v-on:click="logout">
          로그아웃
        </v-btn>
        <v-btn rounded class="ma-2" color="#8c82fc" v-on:click="moveDetail">
          회원정보
        </v-btn>
        </div>
        <div v-if="!loginStatus">        
        <v-btn rounded class="ma-2" color="#2eb872" to="/user/logintest" >
            <v-icon>mdi-account-outline</v-icon>로그인
          </v-btn>
        </div>
        
    </v-app-bar>
    </div>
    </v-app>
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
        this.$router.push("/user/detail?uid="+this.nickname);
    },
    moveSearch(){
      this.$router.push("/search");
    }
  },
  data() {
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
