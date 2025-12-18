package alatoo.collabspace.controllers;

import alatoo.collabspace.dto.AuthResponse;
import alatoo.collabspace.dto.LoginRequest;
import alatoo.collabspace.dto.RegisterRequest;
import alatoo.collabspace.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
