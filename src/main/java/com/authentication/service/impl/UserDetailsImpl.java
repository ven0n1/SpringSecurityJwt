package com.authentication.service.impl;

import com.authentication.domain.entity.AuthUser;
import com.authentication.domain.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private final AuthUserServiceImpl service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AuthUser user = service.findAuthUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with login %s not found", login));
        }
        return User
                .withUsername(login)
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
