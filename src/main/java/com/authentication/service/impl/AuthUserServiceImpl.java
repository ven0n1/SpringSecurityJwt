package com.authentication.service.impl;

import com.authentication.domain.entity.AuthUser;
import com.authentication.repository.AuthUserRepository;
import com.authentication.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository repository;

    @Override
    public AuthUser findAuthUserByLogin(String login) {
        return repository.findAuthUserByLogin(login);
    }

    @Override
    public Boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public AuthUser save(AuthUser user) {
        return repository.save(user);
    }
}
