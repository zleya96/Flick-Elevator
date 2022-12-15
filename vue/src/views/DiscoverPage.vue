<template>
<div>
    <nav-bar />
    
    <movie-card :movie="this.$store.state.randomMovie"/>
    
    </div>
</template>

<script>
import MovieCard from '../components/MovieCard.vue'
import NavBar from '../components/NavBar.vue'

export default {
  
  name: 'discover',
  components: { NavBar, MovieCard },
 
  
}

</script>

<style>

button:hover{
  
    transform: scale(1.02);
    box-shadow: 0px 0px 80px -25px rgba(0,0,0, 0.5);
    transition: all 0.4s;
  }
</style>