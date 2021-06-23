package com.khanhdpdx.webapishoplaptop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO{
    private LaptopDTO laptop;

    private Integer quantity;

    private Float unitPrice;

    private Integer discount;
}
