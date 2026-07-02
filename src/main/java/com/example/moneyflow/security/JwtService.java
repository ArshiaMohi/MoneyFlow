package com.example.moneyflow.security;

import com.example.moneyflow.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class JwtService {
    private static final String SECRET_KEY =
            "ZmQ5ZGI3NjNmMWQ0NDc4Mjg3ODNkMzY2M2Q5ODUwY2Q2MjMzZDY2ZjQ0MGM4ZjA5YjI2NjRkMWMxOTViNjZmYg==";
    private static final long EXPIRATION_TIME = 100 * 60 * 60 * 24;

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
