package com.neusoft.elmboot.jwt;

import com.neusoft.elmboot.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil {

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", user.getUserId());
        return Jwts.builder()
                .addClaims(map).setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String parseTokenUser(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("user").toString();
        } catch (Exception e) {
            return null;
        }
    }


}
