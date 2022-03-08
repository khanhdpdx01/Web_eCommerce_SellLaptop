import ProductService from "../../services/Product";
const service = new ProductService();

const state = {
    productPage: {
        products: [],
        currentPage: 0,
        totalItems: 0,
        totalPages: 0
    }
}

const mutations = {
    SET_PRODUCT_PAGE(state, productPage) {
        state.productPage.currentPage = productPage.currentPage;
        state.productPage.totalItems = productPage.totalItems;
        state.productPage.totalPages = productPage.totalPages;
        state.productPage.products = [...productPage.data];
    }
}

const actions = {
    async getAllProduct({ commit }, productPage) {
        let response = await service.findAll(productPage);
        commit('SET_PRODUCT_PAGE', response.data);
    },
}

const getters = {
    getProductPage: (state) => state.productPage,
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}