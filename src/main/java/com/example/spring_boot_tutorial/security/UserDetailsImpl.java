package com.example.spring_boot_tutorial.security;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_boot_tutorial.entity.User;

public class UserDetailsImpl implements UserDetails {
    private UUID id;
    private String username;
    private String email;
    private String loginId;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(
        UUID id,
        String username,
        String email,
        String loginId,
        String password,
        Collection<? extends GrantedAuthority> authorities
    ){
        this.id=id;
        this.username=username;
        this.email=email;
        this.loginId=loginId;
        this.password=password;
        this.authorities=authorities;
    }

    public static UserDetailsImpl build(User user,Set<GrantedAuthority> authorities){
        
            return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getLoginId(),
                user.getPassword(),
                authorities
            );
    }

    public UUID getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getLoginId(){
        return loginId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
