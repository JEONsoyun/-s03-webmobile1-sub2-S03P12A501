<template>
<v-app id="inspire">
    <v-main>
    <div class="post">
        <div class="wrapB">
            <h2>전체글</h2>
            <section class="post-list">
            <button class="btn" v-on:click="moveWrite">
                    글작성하기
            </button>
            <div v-for="(post, uid, id) in list" :key="uid">
                    <div class="post-card">
                        <a v-on:click="moveDetail(post.id)">
                        
                            <img :src="getcolor(post.id)" class="post-img"/>
           
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
                                {{post.like}}
                            </span>
                        </div>
                </div>
            </div>
            
            
            </section>
            <div class="tag-list-wrap">
            <v-btn v-on:click="scrollToTop" class="button-bottom" color="#ffb367"><v-icon>mdi-arrow-collapse-up</v-icon></v-btn>
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
    
    </v-main>
    <infinite-loading  v-on:infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
  </v-app>

</template>
 
<script>
import InfiniteLoading from 'vue-infinite-loading';
import '../../assets/css/post.scss';
import axios from "axios";
const storage = window.sessionStorage;
export default {
    name:"Post",
    data: () => {
        return {
            page:1,
            list:[],
            photos: [],
            index:3,
            limit:1,
            id:"",
        }
    },
    components:{
        InfiniteLoading,
        list:"",
    
    },
    watch: {
    },
    created() {
            this.nickName = storage.getItem("login_user");
            this.email = storage.getItem("user_email");
            this.id = "1";
            axios.get("http://localhost:8080/feature/board/list")
            .then((res)=>{
                this.list = res.data;
                this.id = res.data.id;
                console.log(this.list);
            })
            .catch((err) => console.error(err));
        },
    //created() {
        // this.nickName = storage.getItem("login_user");
        // axios.get("http://localhost:8080/feature/board/list")
        // .then((res)=>{
        //     this.list = res.data;
        // })
        // .catch((err) => console.error(err));
        // this.getPhotos();
        // this.nickName = storage.getItem("login_user");
        // axios.get("http://localhost:8080/feature/board/list/?id="+this.limit)
        // .then((res)=>{
        //     this.list = res.data
        // })
    },
    methods: {
        getPost() {
        this.nickName = storage.getItem("login_user");
        axios.get("http://localhost:8080/feature/board/list/")
        .then((res)=>{
            this.list = res.data;
        })
        .catch((err) => console.error(err));
        this.getPhotos();
    },
        getPhotos: function () {
        const options = {
            params: {
            _limit: 10,
            },
          }
        axios
            .get("https://jsonplaceholder.typicode.com/photos", options)
            .then((res) => {
            this.photos = [...this.photos];
        getcolor(postnum) {
            let result = this.photos[postnum%10].thumbnailUrl
           
            return result
        },
        moveWrite(){
            this.$router.push("/post/write");
        },
        moveDetail(){
            this.$router.push("/post/postDetail?id="+this.list.id);
        },
        scrollToTop: function () {
        scroll(0, 0);
        },
        infiniteHandler($state) {
        this.nickName = storage.getItem("login_user");
        axios.get("http://localhost:8080/feature/board/list/?id="+(this.limit + 10))
        .then((res)=>{
            console.log('1')
            setTimeout(() => {
                if(res.data.length) {
                    this.list = this.list.concat(res.data);
                    $state.loaded();
                    this.limit +=10
                    if (this.list.length /10 == 0) {
                        $state.complete();
                    }
                } else {
                    $state.complete();
                }
            }, 10 )
        })
        .catch((err) => console.error(err));
        this.getPhotos();
    },
    },
}