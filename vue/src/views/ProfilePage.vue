<template>
  <div>
    <nav-bar />
    <profile-card :user="this.$store.state.user" :account="this.$store.state.account" />
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue'
import ProfileCard from '../components/ProfileCard.vue'
export default {
  components: { ProfileCard, NavBar },
  name: 'profile',

  created() {
    if (this.$store.state.movies.length === 0) {
      this.$store.commit("SET_MOVIES");
    }
  }

}





</script>



<style>

</style>