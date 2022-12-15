<template>
  <div id="register" class="text-center card">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal is-size-3">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <br>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <div class="inline-buttons">

      <input 
      type="checkbox"
      id="adminCheck"
      v-model="user.madeAdminRequest"
      > 
      <div>Request Admin Access</div>

      </div>

      <router-link :to="{ name: 'login' }" class="is-size-5">Have an account?</router-link>
      <button  class="btn btn-lg btn-primary btn-block is-size-4" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        madeAdminRequest: false
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        console.log(this.user);
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>

button:hover{
	background: #6d44b8;
} 

 #register {
   display: flex;
   margin-left: 20vw;
   width: 50vw;
background-color: hsl(0 0% 0% / 0.8);
  color: white; }
 form {
  display: flex; 
  flex-direction: column;
  width: 60vw;
  align-items: center;
  
  
  
  
}
 
</style>
