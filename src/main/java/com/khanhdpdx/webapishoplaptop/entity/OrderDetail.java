package com.khanhdpdx.webapishoplaptop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    private Long laptopId;
    @Id
    private Long orderId;
    /*@ManyToOne
    @JoinColumn(name = "laptopId")
    private Laptop laptop;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;*/

    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Float unitPrice;
    @Column(nullable = true)
    private Integer discount;
}
