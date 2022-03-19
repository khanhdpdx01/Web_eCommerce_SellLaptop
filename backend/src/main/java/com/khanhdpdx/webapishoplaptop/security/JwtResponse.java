package com.khanhdpdx.webapishoplaptop.security;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class JwtResponse implements Serializable {
    private final String accessToken;
    private final String refreshToken;
    private Long userId;
    private String usernameOrEmail;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}