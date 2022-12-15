<template>
  <div class="home">
    <nav-bar />
     <h1>The World of Cinema</h1>
        <p>Instant movie search and convenient database surfing.
          You'll always know what to watch and when.</p> 
         <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue';
export default {
  components: { NavBar },
  name: "home",

  created() {
    // this.$store.commit("SET_MOVIES");
    // this.$store.commit("SET_ACCOUNT");
    this.$router.push('/login');
      
  }
};
</script>
<style scoped>

</style>