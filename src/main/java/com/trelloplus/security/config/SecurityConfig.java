package com.trelloplus.security.config;

import com.trelloplus.security.service.CustomUserDetailsService;
import com.trelloplus.config.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity  // Enables @PreAuthorize & role-based annotations
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomUserDetailsService userDetailsService;

    // Step 1: Password encoder (BCrypt is standard) [ENCRYPTION IS ENABLED, FOR NOW.]
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Step 2: Authentication provider (uses your UserDetailsService)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // your CustomUserDetailsService
        provider.setPasswordEncoder(passwordEncoder());     // bcrypt password matching
        return provider;
    }

    // 🤝 Step 3: Expose AuthenticationManager (Spring will use this in AuthServiceImpl)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 🧱 Step 4: Main Security Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll() // 🔓 Public routes
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // 🔐 Role-secured
                .anyRequest().authenticated()).authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
