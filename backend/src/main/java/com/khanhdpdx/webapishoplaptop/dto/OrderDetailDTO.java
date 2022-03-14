package com.khanhdpdx.webapishoplaptop.dto;

import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailDTO {
    private LaptopDTO laptop;

    private Integer quantity;

    private Float price;

    private Integer discount;
}
