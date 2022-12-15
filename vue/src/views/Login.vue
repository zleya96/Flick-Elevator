<template>
    
  
  <div id="login" class="card">
    <about-us class="aboutUs"/>
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal is-size-3">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="label is-size-5 has-text-white"><i class="fa-regular fa-user"></i>&nbsp;Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="label is-size-5 has-text-white"><i class="fa-solid fa-unlock-keyhole"></i>&nbsp;Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" class="is-size-5">Need an account?</router-link>
      <button type="submit" class="is-size-4">Sign in</button>
    </form>
    
  </div>
</template>

<script>
import AboutUs from '../components/AboutUs.vue';
import authService from "../services/AuthService";
import AccountService from "../services/AccountService.js"

export default {
  name: "login",
  components: {AboutUs},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            AccountService.getUserAccount(this.$store.state.user.id).then(response => {
             if (response.status == 200) { 
               this.$store.commit("SET_ACCOUNT", response.data);
               this.$store.commit("SET_MOVIES");
              if (this.$store.state.account.preferredGenres === "") {
                this.$router.push("/setgenres");
                } else {
                  this.$router.push("/discover");
                }
              }
            });
          }
        }).catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>
.card {
  display: flex;
  justify-content: flex-start;
  background-color: hsl(0 0% 0% / 0.8);
  
}
#login{
  background-color: hsl(0 0% 0% / 0.8);
  color: white;
}

.aboutUs{
  background-color:rgb(96, 74, 146);
  width: 130%;
}
.form-signin{
  display: flex;
  justify-content: center;
  width: 70%;
}



</style>
