package com.loanengine.LoanEngine.auth.internal.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtTokenGenerator {
    private final String secret;
    private final Long jwtExpiration;

    public JwtTokenGenerator(@Value("${jwt.secret}") String secret,@Value("${jwt.expiration}") Long jwtExpiration) {
        this.secret = secret;
        this.jwtExpiration = jwtExpiration;
    }

    private SecretKey getKey(){
        byte[] bytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(UUID userID, String username, Collection<? extends GrantedAuthority> authorities){
        String authority = authorities
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        return Jwts.builder()
                .subject(username)
                .claims()
                .add("userId",userID)
                .add("role",authority)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .and()
                .signWith(getKey())
                .compact();
    }
}
