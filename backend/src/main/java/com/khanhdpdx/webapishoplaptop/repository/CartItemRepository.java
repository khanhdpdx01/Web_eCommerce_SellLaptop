package com.khanhdpdx.webapishoplaptop.repository;

import com.khanhdpdx.webapishoplaptop.entity.CartItem;
import com.khanhdpdx.webapishoplaptop.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    Optional<CartItem> findByCartIdAndLaptopId(Long cardId, Long laptopId);
}
