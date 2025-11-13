package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.RefreshToken;
import alatoo.collabspace.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(User user);

    List<RefreshToken> findAllByUser(User user);
}