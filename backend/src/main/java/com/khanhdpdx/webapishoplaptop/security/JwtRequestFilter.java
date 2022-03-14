package com.khanhdpdx.webapishoplaptop.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.khanhdpdx.webapishoplaptop.constant.ApplicationConstant.ACCESS_TOKEN_COOKIE;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Optional<Cookie> cookieToken = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> ACCESS_TOKEN_COOKIE.equals(cookie.getName()))
                .findFirst();

        try {
            if (cookieToken.isPresent()) {
                final String token = cookieToken.get().getValue();

                if (jwtTokenUtil.validateToken(token)) {
                    UserDetails userDetails = setSecurityContext(request, token);
                }
            }
        } catch (ExpiredJwtException e) {
            System.out.println("Token is expired");
        }
        filterChain.doFilter(request, response);
    }

    // Get user identity and set it on spring security context
    private UserDetails setSecurityContext(HttpServletRequest request, String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ? List.of() : userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
        return userDetails;
    }
}