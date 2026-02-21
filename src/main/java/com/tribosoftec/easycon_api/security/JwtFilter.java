package com.tribosoftec.easycon_api.security;

import com.tribosoftec.easycon_api.domain.AdmLogin;
import com.tribosoftec.easycon_api.repositories.AdmLoginRepositrory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final List<String> PUBLIC_URLS = List.of(
            "/api/login/login",
            "/api/login/token",
            "/api/auth"
    );

    private final JwtService jwtService;
    private final AdmLoginRepositrory admLoginRepositrory;

    public JwtFilter(JwtService jwtService, AdmLoginRepositrory admLoginRepositrory) {
        this.jwtService = jwtService;
        this.admLoginRepositrory = admLoginRepositrory;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return PUBLIC_URLS.stream().anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtService.extractEmail(token);

            AdmLogin login = admLoginRepositrory.findByEmail(email);

            if (login != null) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                login.getEmail(),
                                null,
                                List.of()
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}

