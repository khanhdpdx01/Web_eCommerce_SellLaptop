package com.khanhdpdx.webapishoplaptop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailDTO {
    private LaptopDTO laptop;

    private Integer quantity;

    private Float unitPrice;

    private Integer discount;
}
