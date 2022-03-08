import http from './index'

class ProductService {
    findAll(roomPage) {
        return http.get('/product', { params: roomPage })
    }
}

export default ProductService;