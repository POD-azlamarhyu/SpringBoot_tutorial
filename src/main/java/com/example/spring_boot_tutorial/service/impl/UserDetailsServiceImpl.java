package com.example.spring_boot_tutorial.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginIdOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByLoginIdOrEmail(loginIdOrEmail, loginIdOrEmail).orElseThrow(
            () -> new UsernameNotFoundException("User not found with Login id or Email: "+ loginIdOrEmail)
        );

        Set<GrantedAuthority> authorities = user
            .getRole()
            .stream()
            .map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
        
        return UserDetailsImpl.build(user,authorities);
    }
}
