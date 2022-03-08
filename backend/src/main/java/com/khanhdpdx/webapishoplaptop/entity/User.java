package com.khanhdpdx.webapishoplaptop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(columnDefinition = "nvarchar(50) not null")
    private String name;
    @Column(columnDefinition = "varchar(50) not null")
    private String email;
    @Column(columnDefinition = "nvarchar(255) not null")
    private String address;
    @Column(columnDefinition = "varchar(15) not null")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private Integer status;

/*    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Order> orders;*/

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
  /*  @Column(name = "role_id")
    private Long roleId;*/
}
