package com.labs.lab4.controller;

import com.labs.lab4.dto.request.SignInRequest;
import com.labs.lab4.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @GetMapping("/history")
    public ResponseEntity<?> getHistoryPagesNum() {
        return authService.getHistoryPagesNum();
    }

    @GetMapping("/history/{page}")
    public ResponseEntity<?> getHistoryPage(@PathVariable Integer page) {
        return authService.getHistoryPage(page);
    }
}
