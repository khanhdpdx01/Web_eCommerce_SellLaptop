import axios from 'axios';

export default axios.create({
    baseURL: process.env.VUE_APP_SERVICE_ENDPOINT, //|| 'http://localhost:3000/'
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    },
    timeout: 1500,
    withCredentials: true
})