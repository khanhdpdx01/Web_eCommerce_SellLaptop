package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.security.*;
import com.khanhdpdx.webapishoplaptop.utils.CookieFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.khanhdpdx.webapishoplaptop.constant.ApplicationConstant.ACCESS_TOKEN_COOKIE;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;


    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil,
                                    UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
                                                       HttpServletResponse response, HttpServletRequest request) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        final String accessToken = jwtTokenUtil.generateToken(userDetails);

        Cookie accessTokenCookie = CookieFactory.create(ACCESS_TOKEN_COOKIE, accessToken);

        response.addCookie(accessTokenCookie);

        JwtResponse jwtResponse = new JwtResponse(accessToken, null);
        jwtResponse.setUserId(userDetails.getUserId())
                .setUsernameOrEmail(userDetails.getUsername())
                .setRoles(userDetails.getRoles());

        return ResponseEntity.status(200).body(jwtResponse);
    }
}
