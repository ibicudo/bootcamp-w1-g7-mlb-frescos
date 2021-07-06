package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.LoginFailedException;

import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization").split(" ")[1];
        if (existeJWTToken(request, response)) {
            if (! JWTUtil.validateToken(token))
                throw new IOException();
        } else {
            throw new LoginFailedException("deu ruim :/");
        }
        System.out.println(JWTUtil.getId(token));
        filterChain.doFilter(request, response);
    }

    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader("Authorization");
        if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer "))
            return false;
        return true;
    }
}
