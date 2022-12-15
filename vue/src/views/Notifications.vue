<template>
  <section>
      <nav-bar />
    <admin-notifications />
  </section>
</template>

<script>
import AdminNotifications from '../components/AdminNotifications.vue'
import NavBar from '../components/NavBar.vue'
export default {
    components: {
        AdminNotifications,
        NavBar
    },

    created() {
      if (this.$store.state.allUsers.length == 0) {
        this.$store.commit("SET_ALL_USERS");
      }
    }
}
</script>

<style>

</style>