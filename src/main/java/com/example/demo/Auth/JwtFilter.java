package com.example.demo.Auth;

import com.example.demo.Person.Person;
import com.example.demo.Person.PersonRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PersonRepository personRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/auth");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String jwt = authorization.substring("Bearer ".length());
        String userName = jwtService.getUserName(jwt);
        Optional<Person> person = personRepository.findByUserName(userName);

        if (person.isPresent()) {
            List<GrantedAuthority> authorities = List.of(() -> person.get().isAdmin() ? "admin" : "user");
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}
