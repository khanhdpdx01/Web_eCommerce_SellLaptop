import http from './index'

class ProductService {
    findAll(roomPage) {
        return http.get('/product', { params: roomPage })
    }
    getProductBySlug(slug) {
        return http.get(`/product/${slug}`)
    }
}

export default ProductService;