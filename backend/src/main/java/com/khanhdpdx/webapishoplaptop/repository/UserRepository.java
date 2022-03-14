package com.khanhdpdx.webapishoplaptop.repository;

import com.khanhdpdx.webapishoplaptop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :usernameOrEmail or u.email = :usernameOrEmail")
    Optional<User> findFirstByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    Optional<User> findFirstByUsername(String username);

    User findFirstByEmail(String email);
}
