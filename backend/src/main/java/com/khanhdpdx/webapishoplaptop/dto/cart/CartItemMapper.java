package com.khanhdpdx.webapishoplaptop.dto.cart;

import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper
public interface CartItemMapper {
    CartItemMapper MAPPER = Mappers.getMapper(CartItemMapper.class);

    default CartItem from(LaptopDTO laptopDTO, Long cartId, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setLaptopId(laptopDTO.getLaptopId());
        cartItem.setQuantity(quantity);
        cartItem.setDiscount(laptopDTO.getDiscount());
        cartItem.setPrice(quantity * laptopDTO.getPrice());
        cartItem.setShipFee(50000f);
        return cartItem;
    }
}
