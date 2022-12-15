<template>
  <nav class="navbar " aria-label="main navigation">
    <div class="navbar-brand">
      <router-link class="navbar-item" :to="{ name: 'discover' }">
        <img src="../assets/film.png" height="50" />
        <p class="is-size-3 has-text-weight-bold has-text-white" id="name">Flick Elevator</p>
      </router-link>

      <a
        role="button"
        class="navbar-burger"
        aria-label="menu"
        aria-expanded="false"
        data-target="navbarBasicExample"
        @click="toggleIsActive"
      >
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>

    <div class="navbar-menu" :class="{ 'is-active': isActive }">
      <div class="navbar-end">
        <router-link v-show="isAdmin" class="navbar-item has-text-white	is-size-5" :to="{ name: 'admin' }"
          >Admin</router-link
        >

        <router-link class="navbar-item is-size-5 has-text-white	" :to="{ name: 'discover' }"
          >Discover</router-link
        >

        <router-link class="navbar-item is-size-5 has-text-white	" :to="{ name: 'browse' }"
          >Browse</router-link
        >

        <router-link class="navbar-item is-size-5 has-text-white	" :to="{ name: 'profile' }"
          >Profile</router-link
        >
        <router-link
          class="navbar-item is-size-5 has-text-white	"
          :to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      isActive: false,
    };
  },
  methods: {
    toggleIsActive() {
      this.isActive = !this.isActive;
    },
  },
  computed: {
    isAdmin() {
      return this.$store.state.user.authorities[0].name === 'ROLE_ADMIN';
    }
  }
};
</script>

<style>
.navbar {
   background-color: hsl(0 0% 0% / 0.8);  
  color: #ffffff;
  border-radius: 10px;
}
.navbar-menu.is-active {
   background-color: hsl(0 0% 0% / 0);  
  color: #ffffff;  
  border-radius: 10px;
}

a.navbar-item {
  font-family: 'Unbounded';
}
a.navbar-item:hover {
    background-color:#573b8a;
    border-radius: 10px;
    box-shadow: inset 0 0 10px #000;
}

.navbar-burger {
  color: #ffffff;
}
#name {
  padding-left: 5px;
  font-family: 'Unbounded','Roboto','Avenir', Helvetica, Arial, sans-serif;
}
</style>