package com.example.spring_boot_tutorial.service.impl;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_tutorial.entity.RefreshToken;
import com.example.spring_boot_tutorial.entity.Role;
import com.example.spring_boot_tutorial.entity.User;
import com.example.spring_boot_tutorial.payload.LoginDTO;
import com.example.spring_boot_tutorial.payload.RegisterDTO;
import com.example.spring_boot_tutorial.repository.RefreshTokenRepository;
import com.example.spring_boot_tutorial.repository.RoleRepository;
import com.example.spring_boot_tutorial.repository.UserRepository;
import com.example.spring_boot_tutorial.security.JWTTokenProvider;
import com.example.spring_boot_tutorial.security.UserDetailsImpl;
import com.example.spring_boot_tutorial.service.AuthService;
import com.example.spring_boot_tutorial.service.RefreshTokenService;
import com.example.spring_boot_tutorial.utils.JWTAuthResponse;

@Service
public class AuthServiceImpl implements AuthService {
    
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTTokenProvider jwtTokenProvider;


    @Autowired
    private RefreshTokenService refreshTokenService;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                            UserRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder,
                            JWTTokenProvider jwtTokenProvider){
        this.authenticationManager=authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider=jwtTokenProvider;
    }

    @Override
    public Map<String, String> login(LoginDTO loginDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLoginIdOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateJWTToken(authentication);
        RefreshToken savedRefreshToken = refreshTokenService.createRefreshToken(authentication);
        String refreshToken = savedRefreshToken.getToken();

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token",refreshToken);

        return tokens;
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        
        final String normalUser = "Normal";

        if(userRepository.existsByEmail(registerDTO.getEmail())){
            return "Email is already exists.";
        }

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setLoginId(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> setRole = new HashSet<>();
        Role role = roleRepository.findByRoleName(normalUser).get();
        setRole.add(role);
        user.setRole(setRole);
        
        userRepository.save(user);

        return "User registered Successfully !!";
    }

    @Override
    public ResponseEntity<?> refreshToken(Optional<RefreshToken> refreshToken){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return refreshToken.map(
            refreshTokenService::verifyRefreshTokenExpiration
        ).map(
            RefreshToken::getUser
        ).map(
            user -> {
                String accessToken = jwtTokenProvider.generateJWTToken(authentication);
                JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
                jwtAuthResponse.setAccessToken(accessToken);

                return ResponseEntity.ok(jwtAuthResponse);
            }
        ).orElseThrow(
            () -> new RuntimeException("Refresh token is not in database")
        );
    }
}
