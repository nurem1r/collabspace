package alatoo.collabspace.services;


import alatoo.collabspace.dto.AuthResponse;
import alatoo.collabspace.dto.LoginRequest;
import alatoo.collabspace.dto.RegisterRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);

}
