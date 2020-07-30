<template>
  <div>
      삭제중...
  </div>
</template>

<script>
import axios from 'axios';
const storage = window.sessionStorage;

export default {
    created() {
        this.uid = storage.getItem("login_user");
        console.log(this.uid);
        axios({
            method:"delete",
            url:"http://i3a501.p.ssafy.io:8080/user/delete/"+this.uid,

        }).then((res)=>{
            let msg = "삭제 처시 문제가 발생했습니다.";
            if(res.data.status){
                msg = "삭제가 완료되었습니다.";
                storage.setItem("jwt-auth-token","");
                storage.setItem("login_user","");
                storage.setItem("user_email","");
                storage.setItem("user_password","");
                storage.clear();
                this.$router.push("/");
            }else{

            }
            alert(msg);
            this.$router.push("/");
        })
    },
    data: function(){
        return {
            uid:''
        };
    }
}
</script>

<style>

</style>