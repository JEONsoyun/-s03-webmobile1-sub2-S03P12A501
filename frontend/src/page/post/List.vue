<template>
    <div class="post">
        <div class="wrapB">
            <h2>전체글</h2>
            <button class="btn" v-on:click="moveWrite">
                    글작성하기
            </button>
            <section class="post-list">
                
                <div v-for="(post, id) in list" :key="id">
                    <div class="post-card">
                        <a v-on:click="moveDetail">
                            <div :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}" class="post-img"/>
                            
                            <div class="contents">
                                <h3>
                                    #{{post.id}}
                                </h3>
                                <h3>
                                    {{post.subject}}
                                </h3>
                                <p class="content">{{post.content}}</p>
                                <span class="date">{{post.date}}</span>  
                                <span class="comment">댓글 {{post.comment}}개</span>
                                
                            </div>
                        </a>

                        <div class="writer-wrap">
                            <a>
                                {{post.nickName}}
                            </a>
                            <span>
                                ♥ {{post.hit}}
                            </span>
                        </div>
                    </div>
                </div>
                
                
                
                
                
            </section>

            <div class="tag-list-wrap">
                <h4>인기태그</h4>
                <ul class="tag-list">
                    <li>
                        #태그1 (8)
                    </li> 
                    <li>
                        #태그2 (5)
                    </li> 
                    <li>
                        #태그3 (2)
                    </li> 
                </ul>
            </div>
        </div>
        

    </div>
</template>
 
<script>
    import '../../assets/css/post.scss';
    import axios from "axios";
    const storage = window.sessionStorage;

    export default {
        name:"Post",
        components:{
            list:""
        },
        watch: {
        },
        created() {
            this.nickName = storage.getItem("login_user");
            this.email = storage.getItem("user_email");

            axios.get("http://localhost:8080/feature/board/list")
            .then((res)=>{
                this.list = res.data;
                console.log(this.list);
            })
        },
        methods: {
            moveWrite(){
                this.$router.push("/post/write");
            },
            moveDetail(){
                this.$router.push("/post/Detail");
            }
        },
        data: () => {
            return {
                list:[]
            }
        }
    }
</script>