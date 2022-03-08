package com.khanhdpdx.webapishoplaptop.dto.order;

import com.khanhdpdx.webapishoplaptop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);
    @Mapping(target = "userId", source = "order.user.userId")
    @Mapping(target = "shipperId", source = "order.shipper.shipperId")
    @Mapping(target = "paymentId", source = "order.payment.paymentId")
    OrderDTO from(Order order);

    List<OrderDTO> fromOrders(List<Order> orders);
}
