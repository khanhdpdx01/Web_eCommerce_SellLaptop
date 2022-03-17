import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from "vuex-persistedstate";
import product from './modules/product';
import user from './modules/user';
import cart from './modules/cart';

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    product,
    user,
    cart
  },
  plugins: [createPersistedState()]
});

export default store;
