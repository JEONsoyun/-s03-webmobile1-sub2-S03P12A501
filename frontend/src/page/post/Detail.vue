<template>
  <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>게시글 정보</h1>
                <div v-if="this.likestatus">
                    <v-btn icon color="#DC143C" v-on:click="likePost(id)" >
                        <v-icon>mdi-heart</v-icon>
                    </v-btn>
                </div>
                <div v-if="!this.likestatus">
                    <v-btn icon color="black" v-on:click="likePost(id)">
                         <v-icon>mdi-heart</v-icon>
                    </v-btn>
                </div>
                <div class="form-wrap">
                    <div class="input-wrap">
<<<<<<< HEAD
                       <p>제목: <input v-model="subject" id="subject" type="text" placeholder=this.subject/>{{this.subject}}</p>
=======
                       <p>제목: <input v-model="subject" id="subject" type="text" placeholder=this.subject/></p>
>>>>>>> 6366d9cb738e0cae8b6354baf9054c1df4e692fc
                        <p>내용:  <input v-model="content" id="content" type="text" placeholder=this.content/>{{this.content}}</p>
                        <p>작성날짜: {{this.created}}</p>
                    </div>

                </div>

<<<<<<< HEAD
                <button class="btn" v-on:click="moveUpdate"> 
=======
                <button class="btn" v-on:click="moveUpdate()"> 
>>>>>>> 6366d9cb738e0cae8b6354baf9054c1df4e692fc
                    <span>
                        정보수정
                    </span>
                </button>
                <button class="btn" v-on:click="moveList">
                    <span>
                        메인화면
                    </span>
                </button>
                <button class="btn" v-on:click="deletePost(id)">
                    <span>
                        글 삭제
                    </span>
                </button>
                    
            </div>
        </div> 
        

    </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/api";

const storage = window.sessionStorage;
console.log(storage);
export default {
    props:{
        id:{
            type:Number,
            required:true,
        }
    },
    data: () => {
            return {
                subject: '',
                content: '',
                created: '',
                likestatus:false,
            }
        },
        methods: {

            moveList(){
                this.$router.push("/");
            },
           
            moveUpdate(){
                console.log(this.subject)
                console.log(this.content)
                axios({
                    method:"put",
                    url:"http://i3a501.ssafy.p.io:8080/feature/board/update",
                    data :{
                        subject : this.subject,
                        content : this.content,
                        created : this.created,
<<<<<<< HEAD
                        
=======
                        id : this.id
>>>>>>> 6366d9cb738e0cae8b6354baf9054c1df4e692fc
                    }
                }).then((res)=>{
                    var msg ;
                    if(res.data.status){
                        msg = "수정이 완료되었습니다.";
                        this.$router.push("/");
                    }
                    alert(msg);
                    this.$router.push("/");
                })
            },
<<<<<<< HEAD
           
=======
>>>>>>> 6366d9cb738e0cae8b6354baf9054c1df4e692fc
            deletePost(postId){
                console.log(postId);
                axios({
                    method:"delete",
                    url:"http://i3a501.p.ssafy.io:8080/feature/board/delete/"+postId,

                    }).then((res)=>{
                    let msg = postId+"번 글이 삭제가 완료됐습니다.";
                    if(res.data.status){
                        msg = "삭제가 완료되었습니다.";
                        this.$router.push("/");
                    }else{

                    }
                    alert(msg);
                    this.$router.push("/");
                })
            },
            likePost(postId){
                axios({
                    method: "GET",
                    url : "http://i3a501.ssafy.p.io:8080/like/"+postId+"/"+storage.getItem("login_user"),
                    
                }).then(
                        this.likestatus = !this.likestatus
                    )
            }
            
        },
        created() {
            axios
                .get("http://i3a501.p.ssafy.io:8080/feature/board/list/detail/{id}?id="+this.id)
                .then((res) => {
                    console.log(res.data);
                    this.subject = res.data.subject;
                    this.content = res.data.content;
                    this.created = res.data.created;
                    this.likestatus = res.data.ilike  
                })
                .catch((err) => console.error(err));
                
            
        },
        
}
</script>

<style>

</style>