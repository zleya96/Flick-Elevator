import axios from 'axios';


export default {

  getAllUsers() {
    return axios.get('/user', {
      headers: { 'Access-Control-Allow-Origin': 'https://localhost:9000' }
    });
  },

  updateUser(userId, user) {
    return axios.put(`/user/${userId}`, user, {
      headers: { 'Access-Control-Allow-Origin': 'https://localhost:9000' }
    });
  },

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  }

}
