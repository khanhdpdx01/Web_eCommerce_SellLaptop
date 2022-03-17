import http from './index'

class CartService {
    getCart() {
        return http.get('/cart');
    }
    addProduct(data) {
        return http.post('/cart/add-product', JSON.stringify(data));
    }
    updateProduct(data) {
        return http.post('/cart/update-quantity', JSON.stringify(data))
    }
    deleteProduct(data) {
        return http.delete('/cart/delete-product', data);
    }
}

export default CartService;