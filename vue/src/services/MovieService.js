import axios from 'axios';

export default {

    /**
     * @returns a list of all movies added to the local database
     */
    getAllMovies() {
        return axios.get('/movie');
    },

    getMovie(movieId) {
        return axios.get(`/movie/${movieId}`);
    },

    /**
     * Adds a movie to the local database
     * @param {object} movie - a movie object created from TMDB values, mapped to the local database table 
     * @returns response status code
     */
    postMovie(movie) {
        return axios.post('/movie', movie);
    },

    deleteMovie(movieId) {
        return axios.delete(`/movie/${movieId}`);
    }

}