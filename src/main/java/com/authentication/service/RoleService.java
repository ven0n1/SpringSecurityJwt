package com.authentication.service;

import com.authentication.domain.entity.EnumRole;
import com.authentication.domain.entity.Role;

public interface RoleService {
    Role findByName(EnumRole name);
}
