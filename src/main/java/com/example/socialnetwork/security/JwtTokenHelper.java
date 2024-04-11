package com.example.socialnetwork.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secret = "jwtTokenWasimKey";

    public String getUsernameFromJWTToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);

    }

    public Date getExpireDateFromJWTToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);

    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);

    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check whether token is expired or not!
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpireDateFromJWTToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims,userDetails.getUsername());

    }


    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY * 100))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromJWTToken(token);
        return ( username.equals(userDetails.getUsername()) && !isTokenExpired(token) );
    }
}
