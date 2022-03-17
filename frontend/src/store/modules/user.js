import UserService from "../../services/UserService";
const service = new UserService();

const state = {
    isAuthenticated: false,
    user: {}
};

const mutations = {
    SET_AUTHENTICATE(state) {
        state.isAuthenticated = !state.isAuthenticated;
    },
}

const actions = {
    async login({ commit }, data) {
        let response = await service.login(data);

        if (response.status === 200)
            commit('SET_AUTHENTICATE');
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