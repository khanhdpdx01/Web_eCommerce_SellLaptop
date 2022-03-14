package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.cart.CreateCartItemDTO;
import com.khanhdpdx.webapishoplaptop.dto.cart.DeleteCartItemDTO;
import com.khanhdpdx.webapishoplaptop.dto.cart.UpdateCartItemDTO;
import com.khanhdpdx.webapishoplaptop.entity.Cart;
import com.khanhdpdx.webapishoplaptop.entity.CartItem;
import com.khanhdpdx.webapishoplaptop.security.UserPrincipal;
import com.khanhdpdx.webapishoplaptop.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public ResponseEntity addProductToCart(@RequestBody CreateCartItemDTO createCartItemDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        Cart cart;

        if (!cartService.isExisted(userDetails.getUserId())) {
            cart = cartService.createCart(userDetails.getUserId());
        } else {
            cart = cartService.findCartByUserId(userDetails.getUserId());
        }

        CartItem cartItem = cartService.addProduct(cart.getCartId(), createCartItemDTO.getLaptopId());
        return ResponseEntity.ok(cartItem);
    }

    @PostMapping("/update-quantity")
    public ResponseEntity updateProductToCart(@RequestBody UpdateCartItemDTO updateCartItemDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        Cart cart = cartService.findCartByUserId(userDetails.getUserId());

        CartItem cartItem = cartService.updateProduct(cart.getCartId(), updateCartItemDTO);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity deleteProductToCart(@RequestBody DeleteCartItemDTO deleteCartItemDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        Cart cart = cartService.findCartByUserId(userDetails.getUserId());

        cartService.deleteProduct(cart.getCartId(), deleteCartItemDTO.getLaptopId());
        return ResponseEntity.ok("Success");
    }
}
