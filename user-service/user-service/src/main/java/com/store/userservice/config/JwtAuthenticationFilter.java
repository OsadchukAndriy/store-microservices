package com.store.userservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Отримуємо заголовок Authorization
        String header = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + header);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("Extracted JWT Token: " + token);

            try {
                // Витягуємо claims із токена
                Claims claims = parseToken(token);
                String username = claims.getSubject();
                System.out.println("Extracted Username: " + username);

                // Перевіряємо, чи не встановлений користувач у SecurityContext
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Встановлюємо аутентифікацію
                    setAuthentication(username, request);
                    System.out.println("User authenticated and set in SecurityContext: " + username);
                }
            } catch (Exception e) {
                // Лог для недійсного токена
                System.out.println("Invalid JWT Token: " + e.getMessage());
            }
        }

        // Передаємо запит далі по ланцюгу
        filterChain.doFilter(request, response);
    }

    /**
     * Метод для парсингу токена
     */
    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtUtil.getSecretKey()) // Отримуємо ключ із JwtUtil
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * Метод для встановлення аутентифікації
     */
    private void setAuthentication(String username, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                username, null, Collections.emptyList()); // Якщо є ролі, заміни на їхній список
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}