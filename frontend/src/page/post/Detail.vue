<template>
  <div class="post" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원정보</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <p>닉네임: {{nickName}}</p>
                        <p>이메일: {{email}}</p>
                        <input v-model="nickName"
                            id="nickname" type="text" readonly/>
                    </div>

                    <div class="input-wrap">
                        <input v-model="email" 
                            id="email"
                            type="text" readonly/>
                    </div>

                </div>
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
                subject: '',
                content: '',
                id:''
            }
        },
        methods: {
            moveList(){
                this.$router.push("/");
            },
            moveUpdate(){
                this.$router.push("/post/update");
            },
            
        },
        created() {
            this.nickName = storage.getItem("login_user");
            this.email = storage.getItem("user_email");
            console.log(this.list.id);
            axios({
                method:"get",
                url:"http://localhost:8080/feature/board/list/detail?id="+this.list.id,
            }).then((res)=>{
                if(res.data.status){
                     console.log(this.list.id);
                }else{

                }
            })
        },
}
</script>

<style>

</style>