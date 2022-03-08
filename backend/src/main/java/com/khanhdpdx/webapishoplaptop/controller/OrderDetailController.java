package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.entity.OrderDetail;
import com.khanhdpdx.webapishoplaptop.service.OrderDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/order-details")
    public List<OrderDetail> getListOrderDetail() {
        return orderDetailService.findAll();
    }
}
