package alatoo.collabspace.repositories;

import alatoo.collabspace.entities.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
}