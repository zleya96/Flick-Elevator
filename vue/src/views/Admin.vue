<template>
  <section>
      <nav-bar></nav-bar>
      <admin-search></admin-search>
  </section>
</template>

<script>


import NavBar from '../components/NavBar.vue';
import AdminSearch from '../components/AdminSearch.vue';
export default {
    
    components: {
        NavBar,
        AdminSearch,
    },
}
</script>

<style>

</style>