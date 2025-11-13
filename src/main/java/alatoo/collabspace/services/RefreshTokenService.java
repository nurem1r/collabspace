package alatoo.collabspace.services;

import alatoo.collabspace.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long userId);

    Optional<RefreshToken> findByToken(String token);

    void verifyExpiration(RefreshToken token);

    int deleteByUserId(Long userId);
}
