package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.cart.UpdateCartItemDTO;
import com.khanhdpdx.webapishoplaptop.entity.Cart;
import com.khanhdpdx.webapishoplaptop.entity.CartItem;

public interface CartService {
    Cart findCartByUserId(Long userId);

    boolean isExisted(Long userId);

    Cart createCart(Long userId);

    CartItem addProduct(Long cartId, Long laptopId);

    CartItem updateProduct(Long cartId, UpdateCartItemDTO updateCartItemDTO);

    void deleteProduct(Long cartId, Long laptopId);
}
