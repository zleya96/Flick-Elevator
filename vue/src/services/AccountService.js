import axios from 'axios';

export default {

    getUserAccount(userId) {
        return axios.get(`/accounts/${userId}`);
        
    },
//do rest of methods
    // updatePreferredGenres(accountId, genres) {
    //     return axios.put(`/accounts/${accountId}/genres`, genres);
    // },

    updateAccount(accountId, account) {
        return axios.put(`/accounts/${accountId}`, account);
    },

    // updateDislikedMovies(accountId, dislikedMovies) {
    //     return axios.put(`/accounts/${accountId}/dislikes`, dislikedMovies);
    // },

    // updateFavorites(accountId, favorites) {
    //     return axios.put(`/accounts/${accountId}/favorites`, favorites);
    // }

}