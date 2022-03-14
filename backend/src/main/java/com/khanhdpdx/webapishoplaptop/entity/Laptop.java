package com.khanhdpdx.webapishoplaptop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long laptopId;
    @Column(name = "name", columnDefinition = "nvarchar(255) not null")
    private String name;
    @Column(nullable = false)
    private Float price;
    @Column(columnDefinition = "nvarchar(255) not null")
    private String linkImage;
    @Column(columnDefinition = "text not null")
    private String description;
    @Temporal(TemporalType.DATE)
    private Date enteredDate;
    @Column(nullable = false)
    private Double discount;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Fetch(FetchMode.JOIN)
    private Category category;
    /*@Column(name = "category_id")
    private Long categoryId;*/

}
