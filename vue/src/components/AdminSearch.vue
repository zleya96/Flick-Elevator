<template>
  <section class="search container">
    <!--for searching TMDB -->

    <div class="user-mgmt">
      <router-link class="has-text-white is-size-5" :to="{ name: 'notifications' }"><i class="fa-solid fa-user-pen"></i>&nbsp;User Management</router-link>
    </div>

    <form class="field" id="search-card">
      <label for="search-term" class="label has-text-white is-size-5">Search By Keyword</label>
      <div class="control">
        <input
          id="search-term"
          class="input"
          type="text"
          placeholder="Search Movies"
          v-model="searchString"
        />
      </div>
      <button id="search-button" v-on:click.prevent="searchMovies">
        <i class="fa-solid fa-magnifying-glass"></i>&nbsp;Search
      </button>
    </form>

    <div class="result-card container" v-for="result in results" :key="result.id">
      <div class="card-image">
        <img
          class="movie-poster"
          :src="'https://image.tmdb.org/t/p/original' + result.poster_path"
          alt=""
        />
      </div>
      <div class="movie-info card-content">
        <h2 class="movie-info is-size-3 has-text-weight-bold movie-title">
          {{ result.title }}
        </h2>
        <h3 class="movie-info is-size-5 genre py-4">{{ convertGenres(result) }}</h3>
        <h3 class="movie-info movie-date has-text-left is-size-5 pb-4"> <i class="fa-regular fa-calendar"></i> {{ result.release_date.substring(0,4) }}</h3>
        <h3 class="movie-info movie-overview has-text-left is-size-5 pb-5">{{ result.overview }}</h3>
        <button class="movie-info button" @click="addMovieToDb(result)">
          <i class="fa-solid fa-plus"></i>&nbsp;Add to Database
        </button>
      </div>
      <!-- <admin-movie-card  ></admin-movie-card> -->
    </div>
  </section>
</template>

<script>
import TmdbService from "../services/TmdbService.js";
import MovieService from "../services/MovieService.js";

//import AdminMovieCard from "../components/MovieCard.vue";
export default {
  components: {
    //AdminMovieCard,
  },

  data() {
    return {
      searchString: "",

      page: 0,
      results: [],
      totalPages: 0,
      totalResults: 0,

      movieToAdd: {},
    };
  },
  
  destroyed() {
     this.$store.commit("SET_MOVIES");
  },

  methods: {
    searchMovies() {
      TmdbService.searchMoviesByString(this.searchString).then((response) => {
        this.page = response.data.page; //see JSON example
        this.results = response.data.results;
        this.totalPages = response.data.total_pages;
        this.totalResults = response.data.total_results;
        console.log(this.results);
      });
    },

    addMovieToDb(result) {
      const convertedGenres = this.convertGenres(result);

      this.movieToAdd = {
        id: result.id,
        title: result.title,
        genres: convertedGenres,
        release_date: result.release_date,
        overview: result.overview,
        popularity: result.popularity,
        poster_path: result.poster_path,
      };
      MovieService.postMovie(this.movieToAdd).then((response) => {
        if (response.status === 200) {
          this.movieToAdd = {};
          //do something
          const resultIndex = this.results.indexOf(result);
          this.results.splice(resultIndex, 1);
        }
      });
    },

    convertGenres(result) {
      let newGenre = result.genre_ids.map((genre) => {
        return this.$store.state.genres[genre];
      });
      return newGenre.join(" ");
    },
  },
};
</script>

<style>
form {
  width: 100%;
  margin: 80px auto;
  
  align-content: center;
}
.search {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.result-card {
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8); 
  color: #ffffff;
  margin: 25px 0px;
  display: grid;
  grid-template-columns: 1fr 2fr;
  overflow: hidden;
}

  .result-card:hover{
    transform: scale(1.02);
    box-shadow: 0px 0px 80px -25px rgba(0,0,0, 0.5);
    transition: all 0.4s;
  }
/* .result-card {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 100% / 0.8);
  color: #0f0c29;
  margin: 25px 0px;
  display: grid;
  grid-template-columns: 1fr 2fr;
  overflow: hidden;
} */

.user-mgmt {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8);  
  color: #ffffff;
  margin: 25px 0px;
  display: grid;
  overflow: hidden;
}

.button {
  color: #fff;
  background: #573b8a;
}

.card-content {
  display: flex;
  flex-direction: column;
}


#search-card {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8);  
  color: #ffffff;
  margin: 25px 0px;
}
#search-term {
  width: 700px;
  max-width: 75vw;
}
#search-button {
  width: 700px;
  max-width: 75vw;
}
.card-image img {
  height: auto;
  width: auto;
  border-radius: 10px;
}
@media only screen and (max-width: 900px) {
  .card-content {
    border: 1px transparent;
  border-radius: 5px;
  color: #fcfcfc;
  margin: 25px 0px;
  display: grid;
    grid-template-columns: 1fr;
  }
  .movie-date, .movie-overview {
    padding: 20px;
  }

  h3 {
    padding: 0px;
  }

  .card-content {
    padding: 0px;
  }
}


</style>
