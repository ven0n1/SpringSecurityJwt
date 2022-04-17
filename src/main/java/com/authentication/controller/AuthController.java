package com.authentication.controller;

import com.authentication.domain.dto.AuthenticationRequestDto;
import com.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestParam String login,
                                               @RequestParam String password) {
        return ResponseEntity.ok(service.authenticate(login, password));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody AuthenticationRequestDto user) {
        return ResponseEntity.ok(service.signUp(user));
    }

    @GetMapping("/hello")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
