package com.khanhdpdx.webapishoplaptop.security;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class UserPrincipal extends User {
    private Long userId;
    private List<String> roles;

    public UserPrincipal(String usernameOrEmail, String password, Collection<? extends GrantedAuthority> authorities) {
        super(usernameOrEmail, password, authorities);
    }
}
