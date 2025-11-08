package com.homestock.gateway.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
public class InternalJwtService {

    @Value("${internal.secret.token}")
    private String INTERNAL_SECRET;

    @Value("${internal.jwt.expiration-ms:300000}")
    private long expirationMs;

    @Value("${internal.jwt.issuer:gateway}")
    private String issuer;

    public String generateToken(String username, List<String> roles, Integer user_id) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(INTERNAL_SECRET));

        return Jwts.builder()
                .setSubject(username)
                .setIssuer(issuer)
                .claim("roles", roles)
                .claim("user_id", user_id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}