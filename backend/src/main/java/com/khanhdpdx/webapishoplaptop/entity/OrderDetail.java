package com.khanhdpdx.webapishoplaptop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
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
    private Float price;
    @Column(nullable = true)
    private Integer discount;

    private Float shipFee;

    private Date createdAt;
}
