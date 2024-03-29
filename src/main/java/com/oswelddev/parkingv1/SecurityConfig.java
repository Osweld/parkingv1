package com.oswelddev.parkingv1;

import com.oswelddev.parkingv1.auth.filters.JWTAuthenticationFilter;
import com.oswelddev.parkingv1.auth.filters.JWTAuthorizationFilter;
import com.oswelddev.parkingv1.auth.services.JWTService;
import com.oswelddev.parkingv1.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JWTService jwtService, BCryptPasswordEncoder passwordEncoder, AuthenticationConfiguration authenticationConfiguration) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationConfiguration = authenticationConfiguration;
    }


    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**", "/bus/v3/api-docs/**");
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.authorizeRequests().antMatchers("/api/v1/token/**","/api/v1/auth/","/swagger-ui/**","/bus/v3/api-docs/**","/v3/api-docs/**").permitAll()
                .antMatchers("/api/**").hasAnyRole("USER","ADMIN","CAJERO").anyRequest().authenticated().and()
                .addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(),jwtService ))
                .addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager(),jwtService)).cors().and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
    }



}
