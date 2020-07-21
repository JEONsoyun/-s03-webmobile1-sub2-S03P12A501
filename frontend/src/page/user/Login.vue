<template>
    <div class="user" id="login">
        <div class="wrapC table">
            <div class="middle">
                <h1>SS_log</h1>
                <div class="input-wrap">
                    <input v-model="email"
                        id="email" 
                        placeholder="이메일을 입력해주세요"
                        type="text"/>
                </div>
                <div class="input-wrap">
                    <input v-model="password" type="password"
                        id="password"
                        placeholder="영문, 숫자 혼용 8자 이상"/>
                </div>
                <div class="input-wrap">
                <button class="btn btn--back btn--login" v-on:click="userLogin">
                    로그인 하기
                </button>
                </div>
                <div class="input-wrap">
                <button class="btn" v-on:click="moveList">
                    메인화면
                </button>
                </div>
                <div class="add-option">
                    <div class="wrap">
                        <p>아직 회원이 아니신가요?</p>
                        <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn--text">
                            회원가입
                        </router-link>
                    </div>

                </div>
                
            </div>
            
        </div>

    </div>
</template>

<script>
    import '../../assets/css/user.scss'
    import constants from '../../lib/constants'
    import axios from 'axios'
    const storage = window.sessionStorage;

    export default {
        components: {
        },
        created(){
        },
        watch: {
        },
        methods: {
            moveList(){
                this.$router.push("/");
            },
            userLogin(){
                storage.setItem("jwt-auth-token","");
                storage.setItem("login_user","");
                axios({
                    method:"get",
                    url:"http://localhost:8080/account/login?email="+this.email+"&password="+this.password,
                }).then((res)=>{
                    if(res.data.status){
                        storage.setItem("jwt-auth-token",res.headers["jwt-auth-token"]);
                        storage.setItem("login_user",res.data.object.uid);
                        
                    }else{
                        alert("로그인에 실패했습니다.");
                    }
                    this.moveList();
                }).catch(e =>{
                    alert("로그인에 실패했습니다.");
                    this.moveList();
                })
            }
        },
        data: () => {
            return {
                constants,
                email: '',
                password: '',

            }
        },
    }

</script>


