package com.authentication.service.impl;

import com.authentication.domain.dto.AuthenticationRequestDto;
import com.authentication.domain.mapper.UserMapper;
import com.authentication.security.JwtTokenProvider;
import com.authentication.service.AuthUserService;
import com.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtTokenProvider provider;
    private final AuthenticationManager authenticationManager;
    private final AuthUserService service;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public String authenticate(String login, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        return provider.createToken(login);
    }

    @Override
    public String signUp(AuthenticationRequestDto user) {
        if (!service.existsByLogin(user.getLogin())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            service.save(mapper.map(user));
            return provider.createToken(user.getLogin());
        } else {
            throw new RuntimeException("Username is already in use");
        }
    }
}
