package com.authentication.domain.dto;

import com.authentication.domain.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@Builder(setterPrefix = "with")
public class UserDTO {
    private String login;
    private String email;
    private Set<Role> roles;
}
