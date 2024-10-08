package com.example.spring_boot_tutorial.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.spring_boot_tutorial.exception.APIException;
import com.example.spring_boot_tutorial.repository.JWTLogoutRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTokenProvider {

    @Value("${spring.app.jwtSecret}")
    private String jwtSecretCode;

    @Value("${spring.app.jwtExpirationMs}")
    private Long jwtExpirationDate;

    @Autowired
    JWTLogoutRepository jwtLogoutRepository;


    public String generateJWTToken(Authentication authentication){
        
        UserDetailsImpl loginUser= (UserDetailsImpl) authentication.getPrincipal();
        String loginId = loginUser.getLoginId();
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .subject(loginId)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretCode));
    }

    public String getLoginIdFromToken(String token){
        String loginId = Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        
        return loginId;
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
            return true;
        }catch(MalformedJwtException malformedJwtException){
            throw new APIException("Invalid JWT Token",HttpStatus.BAD_REQUEST);
        }catch(ExpiredJwtException expiredJwtException){
            throw new APIException("Expired JWT Token",HttpStatus.BAD_REQUEST);
        }catch(UnsupportedJwtException unsupportedJwtException){
            throw new APIException("Unsupported JWT Token",HttpStatus.BAD_REQUEST);
        }catch(IllegalArgumentException illegalArgumentException){
            throw new APIException("JWT claims string is null or emtpy",HttpStatus.BAD_REQUEST);
        }
    }

    public boolean existsLogoutToken(String token){
        boolean result = jwtLogoutRepository.existsByToken(token);
        
        return result;
    }
}
