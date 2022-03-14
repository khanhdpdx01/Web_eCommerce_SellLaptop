package com.khanhdpdx.webapishoplaptop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "cart_item")
@IdClass(CartItemId.class)
public class CartItem {
    @Id
    private Long cartId;
    @Id
    private Long laptopId;

    private Integer quantity;

    private Float price;

    private Integer discount;

    private Float shipFee;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}
