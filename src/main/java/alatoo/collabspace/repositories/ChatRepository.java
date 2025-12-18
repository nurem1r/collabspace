package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}