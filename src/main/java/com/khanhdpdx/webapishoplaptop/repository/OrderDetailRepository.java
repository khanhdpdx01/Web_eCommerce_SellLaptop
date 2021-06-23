package com.khanhdpdx.webapishoplaptop.repository;

import com.khanhdpdx.webapishoplaptop.entity.OrderDetail;
import com.khanhdpdx.webapishoplaptop.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
