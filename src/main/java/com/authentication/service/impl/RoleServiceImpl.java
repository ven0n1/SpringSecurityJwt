package com.authentication.service.impl;

import com.authentication.domain.entity.EnumRole;
import com.authentication.domain.entity.Role;
import com.authentication.repository.RoleRepository;
import com.authentication.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role findByName(EnumRole name) {
        return repository.findByName(name);
    }
}
