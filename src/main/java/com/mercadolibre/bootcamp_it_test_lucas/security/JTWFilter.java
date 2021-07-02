package com.mercadolibre.bootcamp_it_test_lucas.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class JTWFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if ( ! JWTUtil.validateToken(request.getHeader("Token")) )
            // TODO: throw a custom exception    
            throw new IOException();
        
        }


}
