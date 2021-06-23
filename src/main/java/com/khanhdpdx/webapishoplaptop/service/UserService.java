package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.entity.User;

public interface UserService {
    User findFirstByUsername(String username);
}
