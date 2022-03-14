package com.khanhdpdx.webapishoplaptop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CartItemId implements Serializable {
    private Long cartId;
    private Long laptopId;
}
