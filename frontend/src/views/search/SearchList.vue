<template>
  
</template>

<script>
import { mapState } from 'vuex'
export default {
    name:'SearchList',
    computed: {
        ...mapState(['posts'])
    }

}
</script>

<style>

</style>