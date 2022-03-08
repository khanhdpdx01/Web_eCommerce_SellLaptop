package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.payment.PaymentDTO;

import java.util.List;

public interface PaymentService {
    public List<PaymentDTO> findAll();
}
