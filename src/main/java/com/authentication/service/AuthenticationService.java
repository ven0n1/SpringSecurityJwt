package com.authentication.service;


import com.authentication.domain.dto.AuthenticationRequestDto;

public interface AuthenticationService {
    String authenticate(String login, String password);

    String signUp(AuthenticationRequestDto user);
}
