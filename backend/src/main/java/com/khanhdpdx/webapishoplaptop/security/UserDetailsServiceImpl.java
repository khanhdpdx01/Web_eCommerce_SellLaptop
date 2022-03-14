package com.khanhdpdx.webapishoplaptop.security;

import com.khanhdpdx.webapishoplaptop.entity.Role;
import com.khanhdpdx.webapishoplaptop.entity.User;
import com.khanhdpdx.webapishoplaptop.exception.ResourceNotFoundException;
import com.khanhdpdx.webapishoplaptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsernameOrEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User wasn't found"));

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        Set<Role> roles = user.getRoles();

        for (Role role : roles) {
            grantList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        UserPrincipal userDetails = new UserPrincipal(user.getUsername(),
                user.getPassword(), grantList);
        userDetails.setUserId(user.getUserId())
                .setRoles(roles.stream().map(role -> role.getRoleName()).collect(Collectors.toList()));
        return userDetails;
    }
}
