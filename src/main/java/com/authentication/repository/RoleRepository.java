package com.authentication.repository;

import com.authentication.domain.entity.EnumRole;
import com.authentication.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(EnumRole name);
}
