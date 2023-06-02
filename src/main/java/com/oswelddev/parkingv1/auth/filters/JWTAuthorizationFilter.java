package com.oswelddev.parkingv1.auth.filters;

import com.oswelddev.parkingv1.SecurityConfig;
import com.oswelddev.parkingv1.auth.services.JWTService;
import com.oswelddev.parkingv1.auth.services.JWTServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTService jwtService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
        if(!jwtService.requiresAuthentication(header)){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = null;
        if(jwtService.validate(header)){
            authenticationToken = new UsernamePasswordAuthenticationToken(jwtService.getId(header),null,jwtService.getAuthorities(header));
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }



}
