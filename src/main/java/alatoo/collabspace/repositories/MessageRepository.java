package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}