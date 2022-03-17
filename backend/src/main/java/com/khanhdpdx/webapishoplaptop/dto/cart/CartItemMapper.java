package com.khanhdpdx.webapishoplaptop.dto.cart;

import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartItemMapper {
    CartItemMapper MAPPER = Mappers.getMapper(CartItemMapper.class);

    default CartItem fromLaptopDTO(LaptopDTO laptopDTO, Long cartId, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setLaptopId(laptopDTO.getLaptopId());
        cartItem.setQuantity(quantity);
        cartItem.setDiscount(laptopDTO.getDiscount());
        cartItem.setPrice(quantity * laptopDTO.getPrice());
        cartItem.setShipFee(50000f);
        return cartItem;
    }

    default CartItemDTO fromCartItem(CartItem cartItem, LaptopDTO laptopDTO) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setLaptop(laptopDTO);
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setRealPrice(laptopDTO.getPrice());
        cartItemDTO.setDiscountPrice(laptopDTO.getPrice() * (1 - (laptopDTO.getDiscount()) / 100));
        cartItemDTO.setTotalPrice(cartItemDTO.getDiscountPrice() * cartItem.getQuantity());
        cartItemDTO.setShipFee(cartItem.getShipFee());
        return cartItemDTO;
    }
}

