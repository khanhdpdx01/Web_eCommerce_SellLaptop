import ProductService from "../../services/ProductService";
const service = new ProductService();

const state = {
    searchText: "",
    productPage: {
        products: [],
        currentPage: 0,
        totalItems: 0,
        totalPages: 0
    }
}

const mutations = {
    RESET_STATE(state) {
        state.searchText = "";
    },
    SET_SEARCH_TEXT(state, searchText) {
        state.searchText = searchText;
    },
    SET_PRODUCT_PAGE(state, productPage) {
        state.productPage.currentPage = productPage.currentPage;
        state.productPage.totalItems = productPage.totalItems;
        state.productPage.totalPages = productPage.totalPages;
        state.productPage.products = [...productPage.data];
    }
}

const actions = {
    async resetState({ commit }) {
        commit('RESET_STATE');
    },
    async getAllProduct({ commit }, productPage) {
        let response = await service.findAll(productPage);
        commit('SET_PRODUCT_PAGE', response.data);
    }
}

const getters = {
    getProductPage: (state) => state.productPage,
    getSearchText: (state) => state.searchText,
    getProductSplited: (state) => {
        let tmp = [];
        for (let i = 0; i < state.productPage.products.length; i += 5) {
            tmp.push(state.productPage.products.slice(i, i + 5));
        }
        return tmp;
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
}