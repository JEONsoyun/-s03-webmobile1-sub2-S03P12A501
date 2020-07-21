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
        axios({
            method:"delete",
            url:"http://localhost:8080/user?uid="+this.uid,

        }).then((res)=>{
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if(res.data.status){
                msg = "삭제가 완료되었습니다.";
                storage.setItem("jwt-auth-token","");
                storage.setItem("login_user","");
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