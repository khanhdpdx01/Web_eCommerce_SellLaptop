package com.khanhdpdx.webapishoplaptop.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCartItemDTO {
    private Long laptopId;
    private Integer quantity;
}
