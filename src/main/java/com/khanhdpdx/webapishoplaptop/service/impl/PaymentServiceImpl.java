package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.payment.PaymentDTO;
import com.khanhdpdx.webapishoplaptop.dto.payment.PaymentMapper;
import com.khanhdpdx.webapishoplaptop.repository.PaymentRepository;
import com.khanhdpdx.webapishoplaptop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentDTO> findAll() {
        List<PaymentDTO> paymentDTOs = PaymentMapper.MAPPER.fromShippers(paymentRepository.findAll());
        return paymentDTOs;
    }
}
