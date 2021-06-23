package com.khanhdpdx.webapishoplaptop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "orderId")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Temporal(TemporalType.DATE)
    private Date orderedDate;
    @Column(columnDefinition = "nvarchar(255) not null")
    private String shipAddress;
    @Column(nullable = false)
    private Float freightCost;
    @Column(nullable = false)
    private Integer status;

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "user_id")
    private User user;
    /*@Column(name = "user_id")
    private Long userId;*/

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;
    /*@Column(name = "shipper_id")
    private Long shipperId;*/

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    /*@Column(name = "payment_id")
    private Long paymentId;*/
}
