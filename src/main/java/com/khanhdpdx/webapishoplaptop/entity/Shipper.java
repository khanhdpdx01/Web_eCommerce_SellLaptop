package com.khanhdpdx.webapishoplaptop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipper")
public class Shipper {
    @Id
    @Column(name = "shipper_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipperId;

    @Column(name = "company_name", columnDefinition = "nvarchar(255) not null")
    private String companyName;

/*    @OneToMany(mappedBy = "shipper", cascade = CascadeType.ALL)
    private Set<Order> orders;*/

    @OneToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "user_id")
    private User user;
 /*   @Column(name = "user_id")
    private Long userId;*/
}
