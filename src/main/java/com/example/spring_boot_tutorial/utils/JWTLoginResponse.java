package com.example.spring_boot_tutorial.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTLoginResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
