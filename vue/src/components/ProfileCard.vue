<template>
<div id="profile-page-wrapper">
  <div class="profile">
    
    <div>
    <div
      v-for="profileMovie in pageOfItems"
      :key="profileMovie.id"
      class="card profileMovie-card"
    >
      <div class="card-image">
        <img
          v-bind:src="
            'https://image.tmdb.org/t/p/original' + profileMovie.poster_path
          "
        />
      </div>
      <div class="card-content">
        <h3 class="profileMovie-info is-size-3 has-text-weight-bold movie-title">
          {{ profileMovie.title }}
        </h3>
        <h3 class="profileMovie-info is-size-5 has-text-weight-semi-bold genre mt-3">
          {{ profileMovie.genres }}
        </h3>
        <h3 class="profileMovie-info movie-date is-size-5 has-text-left py-5">
          <i class="fa-regular fa-calendar"></i>
          {{ profileMovie.release_date.substring(0, 4) }}
        </h3>
        <h3 class="profileMovie-info movie-overview is-size-5 has-text-left py-6">
          {{ profileMovie.overview }}
        </h3>

        <button
          class="favorite"
          @click="favoriteMovie(profileMovie.id)"
          v-if="!$store.state.account.favoriteMovies.includes(profileMovie.id)"
        >
          <i class="fa-solid fa-star"></i>&nbsp;Favorite Movie
        </button>
        <button
          class="unfavorite"
          @click="unfavoriteMovie(profileMovie.id)"
          v-if="$store.state.account.favoriteMovies.includes(profileMovie.id)"
        >
          <i class="fa-regular fa-star"></i>&nbsp;Unfavorite Movie
        </button>
      </div>
    </div>
    </div>
    <div class="profile-card">
      <p class="is-size-3 is-underlined">My Profile</p>
      <div class="username profileMovie-info is-size-2 has-text-weight-semi-bold is-uppercase">
        <i class="fa-solid fa-user"></i>&nbsp;{{ user.username }}
      </div>
      <p class="is-size-4 is-underlined">My Genres:</p>
      <div class="genre profileMovie-info is-size-5 has-text-weight-bold">
        {{ account.preferredGenres }}
      </div>
      <router-link :to="{ name: 'setGenres' }" class="has-text-white py-6"><i class="fa-solid fa-pen-to-square"></i>&nbsp;Edit My Genres</router-link>
    </div>

  </div>
                   <div class="pagination-container">
  <jw-pagination :items="this.$store.state.likedMoviesArr" @changePage="onChangePage"></jw-pagination>
                   </div>
  </div>
</template>

<script>
import accountService from "../services/AccountService.js";
import JwPagination from 'jw-vue-pagination';


export default {
  components: {JwPagination},
  created() {
    this.$store.commit("SET_LIKED_MOVIES");
    this.$store.commit("SET_DISLIKED_MOVIES");
    this.$store.commit("SET_FAVORITES");
  },

  data() {
    return {
      userLikedMovies: [],
      likeCount: 0,
      dislikeCount: 0,
      favoriteCount: 0,
      pageOfItems: []

    };
  },
  props: ["user", "account"],

  methods: {
    favoriteMovie(id) {
      let favoriteMovieIds =
        this.$store.state.account.favoriteMovies.split(",");
      favoriteMovieIds.push(id);
      this.$store.state.account.favoriteMovies = favoriteMovieIds.join(",");
      localStorage.setItem('account', JSON.stringify(this.$store.state.account));
      this.$store.commit("SET_FAVORITES");

      accountService.updateAccount(
        this.$store.state.account.accountId,
        this.$store.state.account
      );
    },

    unfavoriteMovie(id) {
      let favoriteMovieIds =
        this.$store.state.account.favoriteMovies.split(",");

      const index = favoriteMovieIds.indexOf(id.toString());

      favoriteMovieIds.splice(index, 1);

      this.$store.state.account.favoriteMovies = favoriteMovieIds.join(",");
      localStorage.setItem('account', JSON.stringify(this.$store.state.account));

      this.$store.commit("SET_FAVORITES");

      accountService.updateAccount(
        this.$store.state.account.accountId,
        this.$store.state.account
      );
    },
    onChangePage(pageOfItems) {
            // update page of items
            this.pageOfItems = pageOfItems;
        },
  },
};
</script>

<style>
.profile-card {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8);
  color: #ffffff;
  margin-top: 25px;
  grid-area: card;
}

.profileMovie-card {
  border: 1px transparent;
  border-radius: 10px;
  background-color: hsl(0 0% 0% / 0.8);
  color: #ffffff;
  margin: 25px 0px;
  display: grid;
  grid-template-columns: 1fr 2fr;
  overflow: hidden;
}

.card-image img {
  height: 100%;
  width: 100%;
  border-radius: 10px;
}
.card-content {
  display: flex;
  flex-direction: column;
}
.profile {
  display: grid;
  grid-template-columns: 5fr 1fr;
  grid-template-areas: "profile card";
  gap: 10px;

}

@media only screen and (max-width: 900px) {
  .profileMovie-card {
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


.profile {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: "card"
                       "profile";
}
}
</style>