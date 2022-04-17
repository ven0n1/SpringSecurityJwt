package com.authentication.security;

import com.authentication.domain.dto.UserDTO;
import com.authentication.domain.entity.Role;
import com.authentication.domain.mapper.UserMapper;
import com.authentication.service.AuthUserService;
import com.authentication.service.impl.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    public static final String BEARER_PREFIX = "Bearer ";
    private final UserDetailsImpl userDetailsImpl;
    private final AuthUserService service;
    private final UserMapper mapper;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expiration}")
    private Long expiration;

    @PostConstruct
    void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String login) {
        UserDTO userDTO = mapper.map(service.findAuthUserByLogin(login));
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("roles", userDTO.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        claims.put("email", userDTO.getEmail());
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsImpl.loadUserByUsername(getLogin(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getLogin(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Expired or invalid JWT token\n" + HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
