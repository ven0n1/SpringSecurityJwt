package com.authentication.service;

import com.authentication.domain.entity.AuthUser;

public interface AuthUserService {
    AuthUser findAuthUserByLogin(String login);

    Boolean existsByLogin(String login);

    AuthUser save(AuthUser user);
}
