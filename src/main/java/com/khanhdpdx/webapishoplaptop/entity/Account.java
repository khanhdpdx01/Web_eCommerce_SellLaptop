package com.khanhdpdx.webapishoplaptop.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "username", length = 30,
    columnDefinition = "nvarchar(30) not null")
    private String username;

    @Column(name = "password", length = 30,
            columnDefinition = "nvarchar(30) not null")
    private String password;

    private Integer status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
  /*  @Column(name = "user_id")
    private Long userId;*/
}
