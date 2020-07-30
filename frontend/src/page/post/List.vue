<template>
<v-app id="inspire">
    <v-main>
    <div class="post">
        <div class="wrapB">
            <v-btn color="primary" v-on:click="writePost">글 올리기</v-btn>
            <section class="post-list" >
            <div  v-for="(post, uid) in list" :key="uid">
                    <div class="post-card" v-on:click="showDetail(post.id)">
                        <a>
                            <img :src="getcolor(post.id)" class="post-img"/>
                            <div class="contents">
                                <h3>#{{post.id}}</h3>
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
                                {{post.nickName}}
                            </a>
                            <span>
                                {{post.like}}
                            </span>
                        </div>
                </div>
            </div>
            <!--infinite-loading @infinite="infiniteHandler" slot="append" spinner="waveDots"></infinite-loading -->
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
                .get("http://i3a501.p.ssafy.io:8080/feature/board/list/detail/{id}?id="+id)
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
                console.log(this.photos);
                })
                .catch((err) => console.error(err));
            },
            getPost() {
            this.nickName = storage.getItem("login_user");
            axios.get("http://i3a501.p.ssafy.io:8080/feature/board/list/")
            .then((res)=>{
                this.list = res.data;
            })
            .catch((err) => console.error(err));
        },
        getcolor(postnum) {
                let result = this.photos[postnum+3].thumbnailUrl;
                return result;
            },
            scrollToTop: function () {
            scroll(0, 0);
            },
            infiniteHandler($state) {
            this.nickName = storage.getItem("login_user");
            axios.get("http://i3a501.p.ssafy.io:8080/feature/board/list?id="+this.nickName)
            .then((res)=>{
                setTimeout(() => {
                    if(res.data) {
                        this.list = this.list.concat(res.data);
                        $state.loaded();
                        this.limit+=1
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
}
</style>