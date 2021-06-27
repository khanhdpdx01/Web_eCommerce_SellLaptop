package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.entity.User;
import com.khanhdpdx.webapishoplaptop.repository.UserRepository;
import com.khanhdpdx.webapishoplaptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }
}
