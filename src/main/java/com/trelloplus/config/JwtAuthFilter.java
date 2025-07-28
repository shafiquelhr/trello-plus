package com.trelloplus.config;

import com.trelloplus.model.User;
import com.trelloplus.repository.UserRepository;
import com.trelloplus.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//Filters every request to any other API with Authorization: Bearer token
/*
Step Action
1.	Checks Authorization header for Bearer <token>
2.	Extracts username from token
3.	Validates token with stored user
4.	Sets authentication in Spring Security context
5.	Lets the request continue to controller
*/

//UPDATE: roles support
/*
Added SimpleGrantedAuthority with role	So Spring Security knows what permissions the user has
Used "ROLE_" + user.getRole().name()	Spring expects roles to start with "ROLE_"
Security context now carries roles	    Now you can use .hasRole("ADMIN"), etc.
*/

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    @Autowired
    public JwtAuthFilter(JwtUtil jwtUtil, UserRepository userRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                System.out.println("Invalid JWT: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = (User) userRepo.findByUsername(username).orElse(null);

            if (user != null && jwtUtil.isTokenValid(token, user)) {
                // Add authorities based on a role
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
