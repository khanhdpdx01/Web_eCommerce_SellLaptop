package com.khanhdpdx.webapishoplaptop.dto.order;

import com.khanhdpdx.webapishoplaptop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);
    @Mapping(target = "username", source = "order.user.username")
    @Mapping(target = "companyName", source = "order.shipper.companyName")
    @Mapping(target = "paymentName", source = "order.payment.paymentName")
    OrderDTO from(Order order);

    List<OrderDTO> fromOrders(List<Order> orders);
}
