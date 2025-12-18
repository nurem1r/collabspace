package alatoo.collabspace.services.impl;

import alatoo.collabspace.config.JwtService;
import alatoo.collabspace.dto.AuthResponse;
import alatoo.collabspace.dto.LoginRequest;
import alatoo.collabspace.dto.RegisterRequest;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.AuthMapper;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        AuthResponse response = new AuthResponse();
        response.setToken(
                jwtService.generateToken(
                        userRepository.findByEmail(request.getEmail()).orElseThrow(
                                () -> new NotFoundException("User not found"))
                )
        );
        return response;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new NotFoundException("User with this email is already exists");
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(authMapper.toUser(request));

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(
                jwtService.generateToken(user)
        );
        return authResponse;
    }

}
