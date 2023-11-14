package com.neusoft.elmboot.filter;

import com.neusoft.elmboot.jwt.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Value("${jwt.header}")
    private String headerName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(headerName);

        if (token == null) {
            System.out.println("token is null");
            filterChain.doFilter(request, response);
            return;
        }
        token = token.replace("Bearer ", "");

        String userId = jwtUtil.parseTokenUser(token);
        if (userId == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
                "User"
        );
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(simpleGrantedAuthority);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userId, null, authorityList)
        );
        filterChain.doFilter(request, response);
    }
}
