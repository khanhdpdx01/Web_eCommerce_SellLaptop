import axios from 'axios';
import Vue from "vue";
import router from '../router'

const axiosInstance = axios.create({
    baseURL: process.env.VUE_APP_SERVICE_ENDPOINT, //|| 'http://localhost:3000/'
    headers: {
        'Content-Type': 'application/json; charset=utf-8'
    },
    timeout: 1500,
    withCredentials: true
});

axiosInstance.interceptors.response.use(undefined, function (error) {
    if (error) {
        const originalRequest = error.config;
        if (error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;
            // store.dispatch("LogOut");
            Vue.$toast.error("Vui lòng đăng nhập!", {
                position: "top-right",
                timeout: 5000,
                closeOnClick: true,
                pauseOnFocusLoss: true,
                pauseOnHover: true,
                draggable: true,
                draggablePercent: 0.6,
                showCloseButtonOnHover: false,
                hideProgressBar: true,
                closeButton: "button",
                icon: true,
                rtl: false
            });
            return router.push("/login");
        }
    }
});

export default axiosInstance;