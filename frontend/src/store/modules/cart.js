import CartService from "../../services/CartService";
const service = new CartService();

const state = {
    cart: []
};

const mutations = {
    SET_CART_ITEM(state, cartItem) {
        state.cart.push(cartItem);
    },
    SET_CART(state, cart) {
        state.cart = cart;
    }
}

const actions = {
    async addProduct({ commit }, data) {
        let response = await service.addProduct(data);
        commit('SET_CART', response.data);
    },
    async updateProduct({ commit }, data) {
        let response = await service.updateProduct(data);
        commit('SET_CART', response.data);
    },
    async deleteProduct({ commit }, data) {
        let response = await service.deleteProduct({
            data: data
        });
        commit('SET_CART', response.data);
    },
    async getCart({ commit }) {
        let response = await service.getCart();
        commit('SET_CART', response.data);
    }
}


const getters = {

}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}