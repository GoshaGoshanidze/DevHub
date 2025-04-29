package com.devhub.devhub.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class LoginPageRedirectFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (uri.equals("/login") && auth != null && auth.isAuthenticated()
                && !auth.getPrincipal().equals("anonymousUser")) {
            response.sendRedirect(request.getHeader("Referer") != null ? request.getHeader("Referer") : "/admin/dashboard");
            return;
        }

        filterChain.doFilter(request, response);
    }
}