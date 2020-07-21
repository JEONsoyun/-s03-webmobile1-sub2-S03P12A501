<template>
    <div id="header" v-if="isHeader">
        <h1>
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                SS_log
            </router-link>
        </h1>
         
        <div class="right">
        <h6 v-if="loginStatus"><strong>{{nickname}}</strong>님 환영합니다.   <button class="btn"  v-on:click="logout">
                           로그아웃 
                    </button>
                    <button class="btn" v-on:click="moveDetail">
                            회원정보
                    </button>
                    
                </h6>
                
                <router-link v-if="!loginStatus" v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="login-btn">
                    로그인 
                </router-link>
                </div>
                <div class="right">
            <div class="search-input">
                <i class="fas fa-search"></i>
                <input v-model="keyword" type="text"/>
                
                
            </div> 
            <div>
                

                
            </div>
        </div> 
    </div>
</template>   

<script> 
    import constants from '../../lib/constants'

    const storage = window.sessionStorage;

    export default {
        name: 'Header',
        components: { 

        },
        props: ['isHeader'],
        computed:{
        },
        watch: {
        },
        created() {
        },
        mounted() {
            this.init();
        },
        updated() {
            this.init();
        },
        methods : {
            init(){
                if(storage.getItem("jwt-auth-token")){
                    this.nickname = storage.getItem("login_user");
                    this.loginStatus = true;
                }else{
                    storage.setItem("jwt-auth-token","");
                    this.nickname = "";
                    this.loginStatus = false;
                }
            },
            logout(){
                storage.setItem("jwt-auth-token","");
                storage.setItem("login_user","");
                this.init();
            },
            moveDetail(){
                this.$router.push("/user/detail");
            }
        },
        data: function() {
           return {
               constants,
               keyword : "",
               nickname:"",
               loginStatus:false,
           }
        },

    }
</script>
