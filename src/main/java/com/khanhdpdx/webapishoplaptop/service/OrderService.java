package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAll();

}
