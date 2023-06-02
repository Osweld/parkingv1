package com.oswelddev.parkingv1.auth.controller;


import com.oswelddev.parkingv1.auth.models.AuthUser;
import com.oswelddev.parkingv1.auth.services.JWTService;
import com.oswelddev.parkingv1.auth.services.JWTServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JWTService jwtService;


    public AuthController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/token/refresh")
    ResponseEntity<Map<String,Object>> refreshToken(HttpServletRequest request) throws IOException {
        Map<String,Object> body = new HashMap<>();
        String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
        if(!jwtService.requiresAuthentication(header) || !jwtService.validate(header)){
            body.put("error","there was an error refreshing token");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }

        AuthUser user = new AuthUser( jwtService.getId(header),jwtService.getUsername(header),null,true,true,true,true,jwtService.getAuthorities(header));
        String token = jwtService.refreshToken(user);
        return null;
    }
}
