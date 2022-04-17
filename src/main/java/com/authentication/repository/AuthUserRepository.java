package com.authentication.repository;

import com.authentication.domain.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findAuthUserByLogin(String login);

    Boolean existsByLogin(String login);
}
