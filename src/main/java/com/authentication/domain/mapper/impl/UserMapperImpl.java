package com.authentication.domain.mapper.impl;

import com.authentication.domain.dto.AuthenticationRequestDto;
import com.authentication.domain.dto.UserDTO;
import com.authentication.domain.entity.AuthUser;
import com.authentication.domain.entity.EnumRole;
import com.authentication.domain.mapper.UserMapper;
import com.authentication.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final RoleService service;

    @Override
    public UserDTO map(AuthUser user) {
        return UserDTO.builder()
                .withLogin(user.getLogin())
                .withEmail(user.getEmail())
                .withRoles(user.getRoles())
                .build();
    }

    @Override
    public AuthUser map(AuthenticationRequestDto user) {
        return AuthUser.builder()
                .withLogin(user.getLogin())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .withRoles(Set.of(service.findByName(EnumRole.ROLE_USER)))
                .build();
    }
}
