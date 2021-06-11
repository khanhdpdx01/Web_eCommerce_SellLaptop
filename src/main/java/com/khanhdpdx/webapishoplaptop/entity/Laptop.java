package com.khanhdpdx.webapishoplaptop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
@Table(name = "laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long laptopId;
    @Column(columnDefinition = "nvarchar(255) not null")
    private String name;
    @Column(nullable = false)
    private Float unitPrice;
    @Column(columnDefinition = "nvarchar(255) not null")
    private String linkImage;
    @Column(columnDefinition = "text not null")
    private String description;
    @Temporal(TemporalType.DATE)
    private Date enteredDate;
    @Column(nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    /*@Column(name = "category_id")
    private Long categoryId;*/

}
