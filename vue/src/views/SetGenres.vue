<template>
  <div>
      <nav-bar></nav-bar>
    <div class="genre-card mt-5">
      <p class="is-size-2 has-text-weight-bold">Almost There!</p>
      <p class="is-size-5">To help us best curate your experience, please select some genres you enjoy or want to see:</p>

<div class="container">
  <div class="genre-container">
    <label class="option_item">
      <input type="checkbox" class="checkbox" id="action" value="action" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-person-running"></i></div>
        <div class="name">Action</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="adventure" value="adventure" v-model="preferredGenres">     
<div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-route"></i></div>
        <div class="name">Adventure</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="animation" value="animation" v-model="preferredGenres">      
<div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-paintbrush"></i></div>
        <div class="name">Animation</div>
      </div>
    </label>
    <label class="option_item">
      <input type="checkbox" class="checkbox" id="comedy" value="comedy" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-face-laugh-squint"></i></div>
        <div class="name">Comedy</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="crime" value="crime" v-model="preferredGenres">     
 <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-sack-dollar"></i></div>
        <div class="name">Crime</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="documentary" value="documentary" v-model="preferredGenres">      
<div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-video"></i></div>
        <div class="name">Documentary</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="drama" value="drama" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-masks-theater"></i></div>
        <div class="name">Drama</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="family" value="family" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-people-roof"></i></div>
        <div class="name">Family</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="fantasy" value="fantasy" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-hat-wizard"></i></div>
        <div class="name">Fantasy</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="history" value="history" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-landmark"></i></div>
        <div class="name">History</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="horror" value="horror" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-ghost"></i></div>
        <div class="name">Horror</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="music" value="music" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-music"></i></div>
        <div class="name">Music</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="mystery" value="mystery" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-magnifying-glass"></i></div>
        <div class="name">Mystery</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="romance" value="romance" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-sharp fa-solid fa-heart"></i></div>
        <div class="name">Romance</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="sciFi" value="sciFi" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-brands fa-space-awesome"></i></div>
        <div class="name">Science Fiction</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="thriller" value="thriller" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-exclamation"></i></div>
        <div class="name">Thriller</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="war" value="war" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-person-rifle"></i></div>
        <div class="name">War</div>
      </div>
    </label>
    <label class="option_item">
<input type="checkbox" class="checkbox" id="western" value="western" v-model="preferredGenres">
      <div class="option_inner">
        <div class="tickmark"></div>
        <div class="icon"><i class="fa-solid fa-hat-cowboy"></i></div>
        <div class="name">Western</div>
      </div>
    </label>
    
  </div>
  </div> 



          
      <button v-if="showButton" @click="setPreferredGenres" class="mb-5">Save Genres</button>
      </div>
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue'
import AccountService from '../services/AccountService.js'

export default {

components: {
    NavBar
},

data() {
    return {
        preferredGenres: []
    }
},

//TODO - model previously selected genres?

destroyed() {
    this.preferredGenres = [];
},

computed : {

    showButton() {
        return this.preferredGenres.length > 0;
    }

},

methods: {
    setPreferredGenres() {//conditional to make sure they select something?
        this.$store.state.account.preferredGenres = this.preferredGenres.join(" ");
        AccountService.updateAccount(this.$store.state.account.accountId, this.$store.state.account).then(() => {
            localStorage.setItem('account', JSON.stringify(this.$store.state.account));
            this.$store.commit("SET_RANDOM_MOVIE");
            this.$router.push("/discover");
        });
        
    }
}
}
</script>

<style>
.genre-card {
    background-color: hsl(0 0% 0% / 0.8);  
  color: #ffffff;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  text-align: center;
}

    .instruction {
        font-size:24pt;
        margin-top: 30%;
    }
    .setbutton {
        margin-bottom: 20%;
    }


    .genre-container {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
  padding-bottom: 10px;
}

.container .option_item {
  display: block;
  position: relative;
  width: 175px;
  height: 175px;
  margin: 10px;
}

.container .option_item .checkbox {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  opacity: 0;
}

.option_item .option_inner {
  width: 100%;
  height: 100%;
  background: #CFCCC9;
  border-radius: 10px;
  text-align: center;
  padding: 58px 40px;
  cursor: pointer;
  color: #363430;
  display: block;
  border: 5px solid transparent;
  position: relative;
}

.option_item .option_inner .icon {
  margin-bottom: 10px;
  font-size: 25px;
}

.option_item .option_inner .icon .fab {
  font-size: 32px;
}

.option_item .option_inner .name {
  user-select: none;
}

.option_item .checkbox:checked ~ .option_inner {
  border-color: #6d44b8;
  color: #6d44b8;
}

.option_item .option_inner .tickmark {
  position: absolute;
  top: 0;
  left: -1px;
  border: 20px solid;
  border-color: #6d44b8 transparent transparent #6d44b8;
  display: none;
}

.option_item .option_inner .tickmark:before {
  content: "";
  position: absolute;
  top: -18px;
  left: -20px;
  width: 15px;
  height: 5px;
  border: 3px solid;
  border-color: transparent transparent #fff #fff;
  transform: rotate(-45deg);
}

.option_item .checkbox:checked ~ .option_inner .tickmark {
  display: block;
}

</style>