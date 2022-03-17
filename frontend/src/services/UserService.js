import http from './index'

class UserService {
    login(data) {
        return http.post('/auth/login', JSON.stringify(data));
    }
}

export default UserService;