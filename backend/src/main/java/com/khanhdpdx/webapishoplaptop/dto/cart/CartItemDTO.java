package com.khanhdpdx.webapishoplaptop.dto.cart;

import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    LaptopDTO laptop;

    Integer quantity;

    private Float realPrice;

    private Float discountPrice;

    private Float totalPrice;

    private Float shipFee;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}