package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.order.OrderDTO;
import com.khanhdpdx.webapishoplaptop.entity.Order;
import com.khanhdpdx.webapishoplaptop.repository.OrderRepository;
import com.khanhdpdx.webapishoplaptop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<OrderDTO>> getListOrder() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/order/add")
    public HttpStatus add() {
        return HttpStatus.OK;
    }   

}
