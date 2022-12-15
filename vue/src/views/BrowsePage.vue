<template>
  <div>
    <nav-bar />
<div id="container">
    <div class="select">
      <label class="label has-text-white" for="collection-type">Collection</label>
    <select name="criteria" v-model="criteria" id="collection-type">
      <option value="">All Movies</option>
      <option value="liked">Liked Movies</option>
      <option value="favorite">Favorite Movies</option>
    </select>
    </div>
    <div class="select">
      <label class="label has-text-white" for="genre-select">First Genre</label>
    <select name="genre-select" id="genre-select" v-model="genre1">
      <option value="">Pick a Genre</option>
      <option value="action">Action</option>
      <option value="adventure">Adventure</option>
      <option value="animation">Animation</option>
      <option value="comedy">Comedy</option>
      <option value="crime">Crime</option>
      <option value="documentary">Documentary</option>
      <option value="drama">Drama</option>
      <option value="family">Family</option>
      <option value="fantasy">Fantasy</option>
      <option value="history">History</option>
      <option value="horror">Horror</option>
      <option value="music">Music</option>
      <option value="mystery">Mystery</option>
      <option value="romance">Romance</option>
      <option value="sciFi">Science Fiction</option>
      <option value="thriller">Thriller</option>
      <option value="war">War</option>
      <option value="western">Western</option>
    </select>
    </div>
    <div class="select">
      <label class="label has-text-white" for="genre-select-2">Second Genre</label>
    <select name="genre-select-2" id="genre-select-2" v-model="genre2">
      <option value="">Pick a Second Genre</option>
       <option value="action">Action</option>
      <option value="adventure">Adventure</option>
      <option value="animation">Animation</option>
      <option value="comedy">Comedy</option>
      <option value="crime">Crime</option>
      <option value="documentary">Documentary</option>
      <option value="drama">Drama</option>
      <option value="family">Family</option>
      <option value="fantasy">Fantasy</option>
      <option value="history">History</option>
      <option value="horror">Horror</option>
      <option value="music">Music</option>
      <option value="mystery">Mystery</option>
      <option value="romance">Romance</option>
      <option value="scifi">Science Fiction</option>
      <option value="thriller">Thriller</option>
      <option value="war">War</option>
      <option value="western">Western</option>
    </select>
    </div>
    </div>
    <div class="pagination-container">
    </div>
     <browser-card :browse="browse" :account="$store.state.account" v-for="browse in pageOfItems" v-bind:key="browse.id"/> <!--TODO - alphabetize results? -->
                 <div class="pagination-container">
                 <jw-pagination :items="this.moviesToBrowse" @changePage="onChangePage" class="pagination"></jw-pagination>
                 
    </div>
    </div>
</template>

<script>
import BrowserCard from '../components/BrowserCard.vue'
import NavBar from '../components/NavBar.vue'
import JwPagination from 'jw-vue-pagination';

export default {
  name: 'browse',
  components: { NavBar, BrowserCard, JwPagination },

  data() {
    return {
      criteria: "",
      genre1: "", //modeled to dropdown menus
      genre2: "",
      pageOfItems: []
      
    }
  },

  computed: {

    moviesToBrowse() { //filtered based on drop-downs, replaces $store reference in template
      switch (this.criteria) {
        case "":
          return this.$store.state.movies.filter(movie => movie.genres.includes(this.genre1) && movie.genres.includes(this.genre2));
        case "liked":
          return this.$store.state.likedMoviesArr.filter(likedMovie => likedMovie.genres.includes(this.genre1) && likedMovie.genres.includes(this.genre2));
        case "favorite":
          return this.$store.state.favoriteMoviesArr.filter(favMovie => favMovie.genres.includes(this.genre1) && favMovie.genres.includes(this.genre2));
        default:
          return this.$store.state.movies;

      }

    }

  },

  created() {
    if (this.$store.state.movies.length === 0) {
      this.$store.commit("SET_MOVIES");
    }
  },
  methods: {
        onChangePage(pageOfItems) {
            // update page of items
            this.pageOfItems = pageOfItems;
        }
    }
  
}
</script>

<style>
#container {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8); 
  color: #ffffff;
  margin: 25px 0px;
  padding-top: 15px;
padding-bottom: 50px;
display: flex;
justify-content: space-evenly;
}

select::-ms-expand {
    display: none;
}

select {
  width: 20vw;
}
.pagination-container {
background-color: lightgray;
border-radius: 10px;
font-family: 'Unbounded';
}
a { cursor: pointer; }
        .pagination {
           justify-content: center;
           flex-wrap: wrap;
        }


</style>