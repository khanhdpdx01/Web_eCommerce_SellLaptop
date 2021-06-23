package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.order.OrderDTO;
import com.khanhdpdx.webapishoplaptop.dto.order.OrderMapper;
import com.khanhdpdx.webapishoplaptop.repository.OrderRepository;
import com.khanhdpdx.webapishoplaptop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderDTO> findAll() {
        /*List<OrderDTO> orderDTOS = orderRepository.findAll()
                .stream()
                .map(order -> OrderMapper.MAPPER.from(order))
                .collect(Collectors.toList());*/
        List<OrderDTO> orderDTOS = OrderMapper.MAPPER.fromOrders(orderRepository.findAll());
        return orderDTOS;
    }
}
