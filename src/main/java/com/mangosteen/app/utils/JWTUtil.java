package com.mangosteen.app.utils;

import java.util.Date;
import java.util.HashMap;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {

    public static final int ONE_DAY = 24 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secretKey;


    /**
     * Generate JWT.
     *
     * @param username the specific username
     * @param userId   user id
     * @return the generated JWT.
     */
    public String generateToken(String username, Long userId) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        val claims = new HashMap<String, Object>();
        claims.put("userId", userId);
        return Jwts.builder()
                   .subject(username)
                   .claims(claims)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + ONE_DAY))
                   .signWith(key)
                   .compact();
    }

    public String extractUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Long extractUserId(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("userId", Long.class);
    }

    public boolean validateToken(UserDetails userDetails, String token) {

        return extractUsername(token).equals(userDetails.getUsername())
            && !isTokenExpired(token);
    }

    private Claims getClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
                   .verifyWith(key) // <----
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }
    private boolean isTokenExpired(String token) {
        Date expirationDate = getClaimsFromToken(token).getExpiration();
        return expirationDate.before(new Date());
    }
}
