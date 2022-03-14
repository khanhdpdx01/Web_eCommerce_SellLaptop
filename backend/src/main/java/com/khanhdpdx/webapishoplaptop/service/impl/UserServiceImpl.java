package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.user.UserDTO;
import com.khanhdpdx.webapishoplaptop.entity.Role;
import com.khanhdpdx.webapishoplaptop.entity.User;
import com.khanhdpdx.webapishoplaptop.exception.ResourceNotFoundException;
import com.khanhdpdx.webapishoplaptop.repository.UserRepository;
import com.khanhdpdx.webapishoplaptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.khanhdpdx.webapishoplaptop.constant.ApplicationConstant.USER_ROLE_ID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User wasn't found"));
    }

    @Override
    public Long register(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail())
                .setUsername(userDTO.getUsername())
                .setPassword(passwordEncoder.encode(userDTO.getPassword()))
                .setRoles(new HashSet<>());
        return userRepository.save(user).getUserId();
    }

    @Override
    public boolean existEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

}
