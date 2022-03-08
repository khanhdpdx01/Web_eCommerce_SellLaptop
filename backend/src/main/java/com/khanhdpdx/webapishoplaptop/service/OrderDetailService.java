package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.OrderDetailDTO;
import com.khanhdpdx.webapishoplaptop.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    OrderDetail save(OrderDetailDTO orderDetailDTO, Long orderId);
}
