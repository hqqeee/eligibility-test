package com.sysgears.eligibility.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    @Value("${app.api.key}")  // Get API key from properties
    private String apiKey;

    private static final String AUTHORIZATION_HEADER = "Authorization";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);

        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println("Missing or invalid Authorization header");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid API Key");
            return;
        }

        String requestApiKey = header.substring(7); // Extract API key after "Bearer "


        if (!apiKey.equals(requestApiKey)) {
            System.out.println("Invalid API Key!");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Key");
            return;
        }

        Authentication authentication = new ApiKeyAuthentication(apiKey, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
