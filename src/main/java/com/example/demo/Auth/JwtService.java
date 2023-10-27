package com.example.demo.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String secret = "MySecret";

    public String createJwt(String userName) {
        return Jwts
                .builder()
                .setIssuer("CsBE")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 60 * 60 * 24 * 20))
                .setSubject(userName)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUserName(String jwt) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}