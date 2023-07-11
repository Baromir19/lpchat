package com.lpchat.springboot.chatSbApp.configuration;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class JwtTokenProvider {
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getTokenFromSession(HttpServletRequest request) {
        String token = request.getHeader("Cookie");
        token = token.substring(6); //"token=X" to "X"
        
        if (token == null || token.isEmpty()) {
            HttpSession session = request.getSession(false);
            //System.out.println("Session: " + session);
            
            if (session != null) {
                token = (String) session.getAttribute("token");
                //System.out.println("Token from session: " + token);
            }
        }

        return token;
    }
    
    public String generateToken(String phone) {
        
		String token = Jwts.builder()
                .setSubject(phone)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public void saveTokenInClient(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", token);
        
        // Cookie settings
        cookie.setPath("/"); 
        cookie.setMaxAge(7 * 24 * 60 * 60);
        
        // Cookie to response
        response.addCookie(cookie);
    }
    
    public String getPhoneFromToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
        
        return claims.getBody().getSubject();
    }
}