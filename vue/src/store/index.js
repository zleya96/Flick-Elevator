import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import MovieService from '../services/MovieService'
import AuthService from '../services/AuthService'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));
const currentAccount = JSON.parse(localStorage.getItem('account'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    account: currentAccount || {
      accountId: 0,
      userId: 0,
      preferredGenres: "",
      likedMovies: "",
      favoriteMovies: "",
      dislikedMovies: "",
    },

    genres: {//genre ids for TMDB admin query TODO swap these values for admin movie construction
      28: "action",
      12: "adventure",
      16: "animation",
      35: "comedy",
      80: "crime",
      99: "documentary",
      18: "drama",
      10751: "family",
      14: "fantasy",
      36: "history",
      27: "horror",
      10402: "music",
      9648: "mystery",
      10749: "romance",
      878: "sciFi",
      53: "thriller",
      10752: "war",
      37: "western",
      10770: "tv-movie"
    }, 
    allUsers: [],
    movies: [],
    likedMoviesArr: [],
    dislikedMoviesArr: [],
    favoriteMoviesArr: [],
    randomMovie: {},
    isAdmin: false
      
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
      this.commit("SET_MOVIES");
      this.commit("SET_IS_ADMIN");
      this.commit("SET_ALL_USERS");
    },

    SET_ALL_USERS(state) {
      AuthService.getAllUsers().then(response => {
        state.allUsers = response.data;
      })
    },

    SET_ACCOUNT(state, response) {
      // AccountService.getUserAccount(state.user.id).then(response => {
        localStorage.setItem('account', JSON.stringify(response));
        state.account.accountId = response.accountId;
        state.account.userId = response.userId;
        state.account.preferredGenres = response.preferredGenres;
        state.account.likedMovies = response.likedMovies;
        state.account.favoriteMovies = response.favoriteMovies;
        state.account.dislikedMovies = response.dislikedMovies;
        // this.commit("SET_LIKED_MOVIES");
        // this.commit("SET_DISLIKED_MOVIES");
        // this.commit("SET_FAVORITES");
        //this.commit("SET_RANDOM_MOVIE");
      // });
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('account');
      state.token = '';
      state.user = {};
      state.account = {};
      state.movies = [];
      state.allUsers = [];
      state.dislikedMoviesArr = [];
      state.likedMoviesArr = [];
      state.favoriteMoviesArr = [];
      state.randomMovie = {};
      axios.defaults.headers.common = {};
    },
    SET_MOVIES(state) {
      MovieService.getAllMovies().then(response => {
        state.movies = response.data;
        this.commit("SET_LIKED_MOVIES");
        this.commit("SET_DISLIKED_MOVIES");
        this.commit("SET_RANDOM_MOVIE");
        this.commit("SET_FAVORITES");
        
      });
      
    },
    SET_LIKED_MOVIES(state) {
      const likedMovieIds = state.account.likedMovies.split(',');
      const tempArray = [];

      state.movies.forEach(likedMovie => {
        if (likedMovieIds.includes(likedMovie.id.toString())) {
          tempArray.push(likedMovie);
        }
        state.likedMoviesArr = tempArray;
      });
    },
    SET_DISLIKED_MOVIES(state) {
      const dislikedMovieIds = state.account.dislikedMovies.split(',');
      const tempArray = [];

      state.movies.forEach(dislikedMovie => {
        if (dislikedMovieIds.includes(dislikedMovie.id.toString())) {
          tempArray.push(dislikedMovie);
        }
        state.dislikedMoviesArr = tempArray;
      });
    },
    SET_FAVORITES(state) {
      const favoriteMovieIds = state.account.favoriteMovies.split(',');
      const tempArray = [];

      state.movies.forEach(favoriteMovie => {
        if (favoriteMovieIds.includes(favoriteMovie.id.toString())) {
          tempArray.push(favoriteMovie);
        }
        state.favoriteMoviesArr = tempArray;
      });
    },

    SET_RANDOM_MOVIE(state) {
      const dislikedMovieIds = state.account.dislikedMovies.split(',');
      let isValid = true;
      let index = 0;
      let movie;
      do {
        index = Math.floor(Math.random() * state.movies.length);
        movie = state.movies[index];
        const movieGenreArr = movie.genres.split(" ");

        if (dislikedMovieIds.includes(movie.id.toString()) || !movieGenreArr.some(genre => state.account.preferredGenres.includes(genre))) { //or not interested wait time
          isValid = false;
        } else {
          isValid = true;
        }

      } while (isValid == false);
      state.randomMovie = movie;
      
    },
    SET_IS_ADMIN(state) {
      if(state.user.authorities[0].name === "ROLE_ADMIN") {
        state.isAdmin = true;
      } else {
        state.isAdmin = false;
      }
    }
  }
  
})
