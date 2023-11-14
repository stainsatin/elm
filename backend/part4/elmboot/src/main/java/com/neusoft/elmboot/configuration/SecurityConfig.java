package com.neusoft.elmboot.configuration;

import com.neusoft.elmboot.filter.TokenFilter;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.logout(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/auth/**"
                        ).permitAll()
                        .anyRequest().authenticated()
        );

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler((request, response, accessDeniedException) -> {
            response.getWriter().println("{\"code\":403,\"message\":\"Forbidden\"}");
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }).authenticationEntryPoint((request, response, authException) -> {
            response.getWriter().println("{\"code\":401,\"message\":\"Unauthorized\"}");
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }));
        return http.build();
    }
}
