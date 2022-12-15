<template>
<div>
  <div class="browse-card">
    <div class="card-image">
      <img
        v-bind:src="'https://image.tmdb.org/t/p/original' + browse.poster_path"
      />
    </div>
    <div class="card-content">
      <h2 class="browse-info is-size-3 has-text-weight-bold movie-title">
        {{ browse.title }}
      </h2>
      <h3 class="browse-info is-size-5 has-text-weight-semi-bold genre mt-3">
        {{ browse.genres }}
      </h3>
      <h3 class="browse-info movie-date is-size-5 has-text-left py-5"><i class="fa-regular fa-calendar"></i> {{ browse.release_date.substring(0,4) }}</h3>
      <h3 class="browse-info movie-overview is-size-5 has-text-left pb-6">{{ browse.overview }}</h3>

      <div class="inline-buttons">
      <button class="button is-focused is-danger" @click="dislikeMovie(browse.id)">
        <i class="fa-solid fa-thumbs-down"></i>&nbsp;Dislike
      </button>
      <button class="button is-focused is-success" @click="likeMovie(browse.id)">
        <i class="fa-solid fa-heart"></i>&nbsp;Like!
      </button>
      </div>

      <button
          class="favorite"
          @click="favoriteMovie(browse.id)"
          v-if="!$store.state.account.favoriteMovies.includes(browse.id)"
        >
          <i class="fa-solid fa-star"></i>&nbsp;Favorite
        </button>
        <button
          class="unfavorite is-danger"
          @click="unfavoriteMovie(browse.id)"
          v-if="$store.state.account.favoriteMovies.includes(browse.id)"
        >
          <i class="fa-regular fa-star"></i>&nbsp;Unfavorite
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import accountService from "../services/AccountService.js";

export default {
  props: {
    browse: {
      type: Object,
    },
  },
  data() {
    return {
      likeCount: 0,
      dislikeCount: 0,
      favoriteCount: 0,
    };
  },

  methods: {
    likeMovie(id) {
      if (!this.$store.state.account.likedMovies.includes(id)) {
        this.$store.state.account.likedMovies += "," + id;
        this.$store.commit("SET_LIKED_MOVIES");

        let dislikedMovieIds =
          this.$store.state.account.dislikedMovies.split(",");

        if (dislikedMovieIds.includes(id.toString())) {
          const index = dislikedMovieIds.indexOf(id.toString());

          dislikedMovieIds.splice(index, 1);

          this.$store.state.account.dislikedMovies = dislikedMovieIds.join(",");

          this.$store.commit("SET_DISLIKED_MOVIES");
        }
        localStorage.setItem(
          "account",
          JSON.stringify(this.$store.state.account)
        );

        accountService.updateAccount(
          this.$store.state.account.accountId,
          this.$store.state.account
        );

        this.$store.commit("SET_RANDOM_MOVIE");
      }
    },

    dislikeMovie(id) {
      if (!this.$store.state.account.dislikedMovies.includes(id)) {
      //add this movie info to account list
      this.$store.state.account.dislikedMovies += ',' + id;
      localStorage.setItem('account', JSON.stringify(this.$store.state.account));
      this.$store.commit("SET_DISLIKED_MOVIES");

        let likedMovieIds = this.$store.state.account.likedMovies.split(",");

        if (likedMovieIds.includes(id.toString())) {
          const index = likedMovieIds.indexOf(id.toString());

          likedMovieIds.splice(index, 1);

          this.$store.state.account.likedMovies = likedMovieIds.join(",");

          this.$store.commit("SET_LIKED_MOVIES");
        }
        localStorage.setItem(
          "account",
          JSON.stringify(this.$store.state.account)
        );

        accountService.updateAccount(
          this.$store.state.account.accountId,
          this.$store.state.account
        );

        this.$store.commit("SET_RANDOM_MOVIE");
      }
    },

    favoriteMovie(id) {
      let favoriteMovieIds =
        this.$store.state.account.favoriteMovies.split(",");
      favoriteMovieIds.push(id);
      this.$store.state.account.favoriteMovies = favoriteMovieIds.join(",");
      localStorage.setItem(
        "account",
        JSON.stringify(this.$store.state.account)
      );
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
      localStorage.setItem(
        "account",
        JSON.stringify(this.$store.state.account)
      );

      this.$store.commit("SET_FAVORITES");

      accountService.updateAccount(
        this.$store.state.account.accountId,
        this.$store.state.account
      );
    },
  },
};
</script>

<style>

.browse-card:hover{
    transform: scale(1.02);
    box-shadow: 0px 0px 80px -25px rgba(0,0,0, 0.5);
    transition: all 0.4s;
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
.browse-card {
  border: 1px transparent;
  border-radius: 5px;
  background-color: hsl(0 0% 0% / 0.8);
  color: #ffffff;
  margin: 25px 0px;
  display: grid;
  grid-template-columns: 1fr 2fr;
  overflow: hidden;
}

.inline-buttons {
  display: inline-block;
}

@media only screen and (max-width: 900px) {
  .browse-card {
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

}
</style>
