package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.user.UserDTO;
import com.khanhdpdx.webapishoplaptop.entity.User;

public interface UserService {
    User findFirstByUsername(String username);

    Long register(UserDTO user);

    boolean existEmail(String email);
}
