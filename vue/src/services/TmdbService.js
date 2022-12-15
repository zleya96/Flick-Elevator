import axios from 'axios';

const tmdb = axios.create({
    baseURL: 'https://api.themoviedb.org/3',
    headers: {
        'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmFlMDkxYzFjMGY4OGJmZGFiNzJjOTY5MTc1ZjQzMCIsInN1YiI6IjYzOGEyNzFhM2Y4ZWRlMDA4NmU5ZDg5YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.or024JB5VTtHlI6gMCEvLKgOwnyAahbo9opxbJZJ7oM' 
      }
}
  );

export default {

    /**This API is the broadest, simplest search available for movies. 
     * It appears to search all the string properties of the movie object for a match.
     * The promise includes a property total_pages that can be used to generate page-number <nav> elements for the search results
     * 
     * @param {String} queryString - a string to search by 
     * @param {number} pageNum - a number <= total_pages that indicates which page of results to return
     * @returns an array of movie objects
     */
    searchMoviesByString(queryString, pageNum = 1) {
        queryString = queryString.replaceAll(" ", "%20");
        return tmdb.get(`/search/movie?query=${queryString}&page=${pageNum}&include_adult=false`);
    },

    /**This API returns a list of movies by one to many genre ids, by page number of results.
     * The promise includes a property total_pages that can be used to generate page-number <nav> elements for the search results
     * 
     * @param {number} genreId - number corresponding to genre according to TMDB's docs - TODO: store this data in the vuex
     * @param {number} pageNum - a number <= total_pages that indicates which page of results to return
     * @returns 
     */
    getMoviesByGenre(genreId, pageNum = 1) {
        return tmdb.get(`/discover/movie?with_genres=${genreId}&page=${pageNum}`);
    }


}