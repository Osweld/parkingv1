package com.oswelddev.parkingv1.auth.services;

import com.oswelddev.parkingv1.auth.models.AuthUser;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;

public interface JWTService {

    String create(Authentication auth) throws IOException, NoSuchAlgorithmException;
    boolean validate(String token);
    Claims getClaims(String token);
    String refreshToken(AuthUser authUser) throws IOException;
    String getUsername(String token);
    Long getId(String token);
    Date getExpiration(String token);
    String resolve(String token);
    Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException;
    Boolean requiresAuthentication(String header);
}
