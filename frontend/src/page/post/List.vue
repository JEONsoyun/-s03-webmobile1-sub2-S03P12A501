<template>
<v-app id="inspire">
    <v-main>
    <div class="post">
        <div class="wrapB">
            
            <section class="post-list" >
            <div  v-for="(post, uid) in list" :key="uid">
                <div class="post-card" v-on:click="showDetail(post.id)">
                    <a>
                        <img :src="getcolor(post.id)" class="post-img"/>
                        
                        <div class="contents">
                            <h3>#{{post.id}}  </h3>
                            <v-btn class="mx-2" color="#DC143C" fab x-small dark>
                            <v-icon>mdi-heart</v-icon>
                            </v-btn><small>{{post.lnt}}</small>
                            <h3>
                                제목 : {{post.subject}}
                            </h3>
                            <p class="content">{{post.content}}</p>
                            <span class="date">{{post.created}}</span>  <br/>
                            <span class="comment">댓글 {{post.comment}}개</span>
                            
                        </div>
                    </a>
                    <div class="writer-wrap">
                        <a>
                            글쓴이 : {{post.uid}}
                        </a>
                    </div>
                </div>
            </div>
            <infinite-loading @infinite="infiniteHandler" slot="append" spinner="waveDots"></infinite-loading>
            </section>
            <div class="tag-list-wrap ">
            <v-btn class=" button-bottom" v-on:click="scrollToTop" color="#ffb367"><v-icon>mdi-arrow-collapse-up</v-icon></v-btn>
                <hr/>
                <div class="tag-list">
                    <v-btn class="mx-2 mt-1" dark color="indigo" v-on:click="writePost">
                        <v-icon dark>mdi-pencil</v-icon>
                        글 올리기</v-btn>
                </div>
            </div>
        </div>
    </div>
    
    </v-main>
  </v-app>

</template>
 
<script>
import InfiniteLoading from 'vue-infinite-loading';
import '../../assets/css/post.scss';
import axios from "axios";
const storage = window.sessionStorage;
const api = "http://localhost:8080//"
export default {
    name:"Post",
    data: () => {
        return {
            list:[],
            photos: [],
            limit:1,
        }
    },
    components:{
        InfiniteLoading,
    },
    mounted(){
        this.getPhotos();
        this.getPost();
    },
    methods: {
        showDetail(id){
            axios
                .get(api+"feature/board/list/detail/{id}?id="+id)
                .then((res) => {
                    this.$router.push(`/post/detail/${id}`);
                })
                .catch((err) => console.error(err));
            
        },
        writePost(){
            this.$router.push("/post/write");
        },
        getPhotos: function () {
        axios
            .get("https://jsonplaceholder.typicode.com/photos")
            .then((res) => {
            this.photos = [...this.photos, ...res.data];
            })
            .catch((err) => console.error(err));
        },
        getcolor(postnum) {
            let result = this.photos[postnum+3].thumbnailUrl
            return result
        },
        scrollToTop: function () {
        scroll(0, 0);
        },
        infiniteHandler($state) {
        this.nickName = storage.getItem("login_user");
        axios.get(api+"feature/board/list/"+this.limit)
        .then((res)=>{
            console.log("log"+ res.data)
            setTimeout(() => {
                if(res.data) {
                    this.list = this.list.concat(res.data);
                    $state.loaded();
                    this.limit+=1
                } else if (!res.data.id) {
                    this.limit+=1
                } else {
                    $state.complete();
                }
            }, 500 )
        })
        .catch((err) => console.error(err));
        this.getPhotos();
    },
    },
}
</script>
<style scoped>
.tag-list-wrap[data-v-fbaed234] {
    position: fixed;
    left: 90%;
    width: max-content;
    align-items: center;
    padding: 0;
}
.post .post-list {
    min-height: 500px;
    width: 80vm;
    align-items: left;
}
</style>