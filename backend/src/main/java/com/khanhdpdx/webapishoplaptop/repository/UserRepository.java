package com.khanhdpdx.webapishoplaptop.repository;

import com.khanhdpdx.webapishoplaptop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
    User findFirstByEmail(String email);
}
