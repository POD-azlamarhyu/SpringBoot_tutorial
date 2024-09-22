package com.example.spring_boot_tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.spring_boot_tutorial.security.JWTAuthenticationEntryPoint;
import com.example.spring_boot_tutorial.security.JWTAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService,JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint,JWTAuthenticationFilter jwtAuthenticationFilter){
        this.userDetailsService=userDetailsService;
        this.jwtAuthenticationEntryPoint=jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    }

    @Bean
    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authorize)->
                authorize.requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            ).exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            ).sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
}
