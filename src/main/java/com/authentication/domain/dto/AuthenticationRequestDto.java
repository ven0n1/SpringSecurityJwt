package com.authentication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
public class AuthenticationRequestDto {
    @Length(min = 7, max = 20)
    String login;

    @Email
    String email;

    @Length(min = 7, max = 20)
    String password;
}
