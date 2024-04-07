package com.mangosteen.app.utils;

import java.util.Date;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
     * @param username the specific username/
     * @return the generated JWT.
     */
    public String generateToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                   .subject(username)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + ONE_DAY))
                   .signWith(key)
                   .compact();
    }

    public String extractUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
                   .verifyWith(key) // <----
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public boolean validateToken(UserDetails userDetails, String token) {

        return extractUsername(token).equals(userDetails.getUsername())
            && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        Date expirationDate = Jwts.parser()
                                  .verifyWith(key) // <----
                                  .build()
                                  .parseSignedClaims(token)
                                  .getPayload()
                                  .getExpiration();
        return expirationDate.before(new Date());
    }
}
