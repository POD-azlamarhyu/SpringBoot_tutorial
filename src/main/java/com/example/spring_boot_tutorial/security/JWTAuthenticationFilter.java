package com.example.spring_boot_tutorial.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring_boot_tutorial.repository.JWTLogoutRepository;
import com.example.spring_boot_tutorial.service.impl.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private JWTTokenProvider jwtTokenProvider;
    private UserDetailsServiceImpl userDetailsServiceImpl;
    private JWTLogoutRepository jwtLogoutRepository;

    
    public JWTAuthenticationFilter(
        JWTTokenProvider jwtTokenProvider,
        UserDetailsServiceImpl userDetailsServiceImpl,
        JWTLogoutRepository jwtLogoutRepository
    ){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtLogoutRepository = jwtLogoutRepository;
        
    }

    
    @Override
    protected void doFilterInternal(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        FilterChain filterChain
    ) throws ServletException, IOException{
        String token = getTokenFromRequest(httpServletRequest);

        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token) && !jwtLogoutRepository.existsByToken(token)){
            String loginId=jwtTokenProvider.getLoginIdFromToken(token);
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginId);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }else{
            ;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }else{
            return null;
        }
        
    }
}
