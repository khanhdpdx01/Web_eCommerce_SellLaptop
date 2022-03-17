package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.cart.*;
import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.entity.Cart;
import com.khanhdpdx.webapishoplaptop.entity.CartItem;
import com.khanhdpdx.webapishoplaptop.entity.User;
import com.khanhdpdx.webapishoplaptop.exception.ResourceNotFoundException;
import com.khanhdpdx.webapishoplaptop.repository.CartItemRepository;
import com.khanhdpdx.webapishoplaptop.repository.CartRepository;
import com.khanhdpdx.webapishoplaptop.service.CartService;
import com.khanhdpdx.webapishoplaptop.service.LaptopService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final LaptopService laptopService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(LaptopService laptopService, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.laptopService = laptopService;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart findCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart wasn't found"));

        return cart;
    }

    @Override
    public boolean isExisted(Long userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);

        return cart.isPresent();
    }

    @Override
    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        cart.setUser(new User().setUserId(userId));
        Cart newCart = cartRepository.save(cart);
        return newCart;
    }

    @Override
    public CartItem addProduct(Long cartId, CreateCartItemDTO createCartItemDTO) {
        LaptopDTO laptop = laptopService.findById(createCartItemDTO.getLaptopId());
        Optional<CartItem> cartItemOptional = cartItemRepository.findByCartIdAndLaptopId(
                cartId,
                createCartItemDTO.getLaptopId());
        CartItem cartItem;

        if (cartItemOptional.isPresent()) {
            cartItem = CartItemMapper.MAPPER.fromLaptopDTO(
                    laptop,
                    cartId,
                    cartItemOptional.get().getQuantity() + createCartItemDTO.getQuantity());
            cartItem.setCreatedAt(cartItemOptional.get().getCreatedAt());
            cartItem.setUpdatedAt(new Date());
        } else {
            cartItem = CartItemMapper.MAPPER.fromLaptopDTO(laptop, cartId, createCartItemDTO.getQuantity());
            cartItem.setCreatedAt(new Date());
        }

        CartItem newCartItem = cartItemRepository.save(cartItem);
        return newCartItem;
    }

    @Override
    public CartItem updateProduct(Long cartId, UpdateCartItemDTO updateCartItemDTO) {
        LaptopDTO laptop = laptopService.findById(updateCartItemDTO.getLaptopId());
        cartItemRepository.findByCartIdAndLaptopId(
                cartId,
                updateCartItemDTO.getLaptopId()
        ).orElseThrow(() -> new ResourceNotFoundException("Product wasn't found within cart"));
        CartItem cartItem;

        cartItem = CartItemMapper.MAPPER.fromLaptopDTO(laptop, cartId, updateCartItemDTO.getQuantity());
        cartItem.setUpdatedAt(new Date());

        CartItem newCartItem = cartItemRepository.save(cartItem);
        return newCartItem;
    }

    @Override
    public void deleteProduct(Long cartId, Long laptopId) {
        LaptopDTO laptop = laptopService.findById(laptopId);
        CartItem cartItem = cartItemRepository.findByCartIdAndLaptopId(
                cartId,
                laptopId
        ).orElseThrow(() -> new ResourceNotFoundException("Product wasn't found within cart"));

        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartDTO getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart wasn't found"));


        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cart.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart wasn't found"));

        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        LaptopDTO laptop;

        for (CartItem cartItem : cartItems) {
            laptop = laptopService.findById(cartItem.getLaptopId());
            cartItemDTOs.add(CartItemMapper.MAPPER.fromCartItem(cartItem, laptop));
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartItems(cartItemDTOs);
        cartDTO.setCartId(cart.getCartId());

        return cartDTO;
    }
}
