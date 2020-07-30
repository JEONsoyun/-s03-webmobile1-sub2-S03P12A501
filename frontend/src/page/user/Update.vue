<template>
  <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원정보</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <input v-model="nickName"
                            id="nickname" type="text" readonly/>
                    </div>

                    <div class="input-wrap">
                        <input v-model="email" 
                            id="email"
                            type="text" />
                    </div>

                    
                </div>

                <button class="btn" v-on:click="userUpdate"> 
                    <span>
                        수정하기
                    </span>
                </button>
                <button class="btn" v-on:click="moveDelete"> 
                    <span>
                        회원탈퇴
                    </span>
                </button>
                <button class="btn" v-on:click="moveList">
                    <span>
                        메인화면
                    </span>
                </button>
            </div>


        </div> 
        

    </div>
</template>

<script>
import axios from 'axios';
const storage = window.sessionStorage;
console.log(storage);
export default {
    data: () => {
            return {
                email: '',
                nickName: '',
                password: '',
                passwordConfirm: '',
                passwordType:"password",
                passwordConfirmType:"password",
            }
        },
        created() {
            this.nickName = storage.getItem("login_user");
            this.email = storage.getItem("user_email");
            axios({
                method:"get",
                url:"http://i3a501.p.ssafy.io:8080/user/update?uid="+this.nickName,
            }).then((res)=>{
                if(res.data.status){
                    console.log(res.data);
                    this.email = res.data.object.email;
                }else{

                }
            })
        },
        methods: {
            moveList(){
                this.$router.push("/");
            },
            moveDelete(){
                this.$router.push("/user/delete");
            },
            userUpdate(){
                axios({
                    method:"put",
                    url:"http://i3a501.p.ssafy.io:8080/user/update",
                    data:{
                        email:this.email,
                        uid:this.nickName,
                        createDate:'',
                        userKey:''
                    }
                }).then((res)=>{
                    if(res.data.status){
                        alert("수정이 완료되었습니다.");
                        storage.setItem("jwt-auth-token","");
                        storage.setItem("login_user","");
                        stotage.setItem("user_email","");
                        this.$router.push("/user/logintest");
                    }else{

                    }
                })
            }
        },
}
</script>

<style>

</style>