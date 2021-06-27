package com.khanhdpdx.webapishoplaptop.repository;

import com.khanhdpdx.webapishoplaptop.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
