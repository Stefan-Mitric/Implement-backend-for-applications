package com.example.demo.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class AuthConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/auth/*").permitAll();
                    authorizationManagerRequestMatcherRegistry.requestMatchers("/persons").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/persons/*").hasAnyAuthority("admin", "user");
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/persons/*").hasAuthority("admin");
                    authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
                })
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}
