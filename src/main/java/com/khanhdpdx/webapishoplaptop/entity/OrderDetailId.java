package com.khanhdpdx.webapishoplaptop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class OrderDetailId implements Serializable {
    private Long laptopId;
    private Long orderId;
}
