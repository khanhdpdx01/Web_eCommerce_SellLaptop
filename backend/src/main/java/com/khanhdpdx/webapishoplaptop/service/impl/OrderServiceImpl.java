package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.order.OrderDTO;
import com.khanhdpdx.webapishoplaptop.dto.order.OrderMapper;
import com.khanhdpdx.webapishoplaptop.entity.Order;
import com.khanhdpdx.webapishoplaptop.entity.Payment;
import com.khanhdpdx.webapishoplaptop.entity.Shipper;
import com.khanhdpdx.webapishoplaptop.entity.User;
import com.khanhdpdx.webapishoplaptop.repository.OrderRepository;
import com.khanhdpdx.webapishoplaptop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> orderDTOS = OrderMapper.MAPPER.fromOrders(orderRepository.findAll());
        return orderDTOS;
    }

    public Long createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setFreightCost(orderDTO.getFreightCost());
        order.setOrderedDate(new Date());
        order.setPayment(new Payment().setPaymentId(orderDTO.getPaymentId()));
        order.setShipper(new Shipper().setShipperId(orderDTO.getShipperId()));
        order.setStatus(0);
        order.setShipAddress(orderDTO.getShipAddress());
        order.setUser(new User().setUserId(orderDTO.getUserId()));
        return orderRepository.save(order).getOrderId();
    }
}
