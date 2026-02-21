package com.tribosoftec.easycon_api.security;

import com.tribosoftec.easycon_api.domain.AdmLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class JwtService {

   /* @Value("${JWT_SECRET}")
    private String secret;
*/

    private static final String secret =
            "x6KWKxmlRKPgX9awX7mt3nssi7O3ezk1BEMs3jF56Yc";

    private final Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
