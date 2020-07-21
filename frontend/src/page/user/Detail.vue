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
                            type="text" readonly/>
                    </div>

                    <div class="input-wrap">
                        <input v-model="password"
                            id="password" 
                            type="text" readonly/>
                    </div>

                </div>

                <button class="btn" v-on:click="moveUpdate"> 
                    <span>
                        정보수정
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

export default {
    data: () => {
            return {
                email: '',
                nickName: '',
                password: '',
            }
        },
        methods: {
            moveList(){
                this.$router.push("/");
            },
            moveUpdate(){
                this.$router.push("/user/update");
            }
        },
        created() {
            this.nickName = storage.getItem("login_user");
            axios({
                method:"get",
                url:"http://localhost:8080/user?uid="+this.nickName,
            }).then((res)=>{
                if(res.data.status){
                    console.log(res.data);
                    this.email = res.data.object.email;
                    this.password = res.data.object.password;
                }else{

                }
            })
        },
}
</script>

<style>

</style>