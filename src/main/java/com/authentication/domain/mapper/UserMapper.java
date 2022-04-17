package com.authentication.domain.mapper;

import com.authentication.domain.dto.AuthenticationRequestDto;
import com.authentication.domain.dto.UserDTO;
import com.authentication.domain.entity.AuthUser;

public interface UserMapper {
    UserDTO map(AuthUser user);
    AuthUser map(AuthenticationRequestDto user);
}
