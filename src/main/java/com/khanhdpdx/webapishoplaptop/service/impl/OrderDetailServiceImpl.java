package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.entity.OrderDetail;
import com.khanhdpdx.webapishoplaptop.repository.OrderDetailRepository;
import com.khanhdpdx.webapishoplaptop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }
}
