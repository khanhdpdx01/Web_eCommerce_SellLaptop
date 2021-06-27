package com.khanhdpdx.webapishoplaptop.dto.payment;

import com.khanhdpdx.webapishoplaptop.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {
    PaymentMapper MAPPER = Mappers.getMapper(PaymentMapper.class);

    PaymentDTO from(Payment payment);

    List<PaymentDTO> fromShippers(List<Payment> payments);
}
