package com.example.demo.utils;

import com.example.demo.dto.UserLoginDto;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class TokenUtil {
    private static final int EXPIRATION_TIME = 1000 * 60; // 1 minute
    private static KeyPair keyPair;

    static {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String createJwt(String id) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "ES256")
                .claim("userid", id)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.ES256, keyPair.getPrivate())
                .compact();
        System.out.println(jwtToken);
        return jwtToken;
    }

    public UserLoginDto parseJwt(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(keyPair.getPublic())
                    .parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Date expiration = claims.getExpiration();
            if (expiration != null && expiration.before(new Date())) {
                return null;
            }
            return new UserLoginDto((String) claims.get("userid"));
        }catch (Exception e){
            return null;
        }
    }
}