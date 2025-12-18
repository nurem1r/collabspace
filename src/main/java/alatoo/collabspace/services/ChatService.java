package alatoo.collabspace.services;

import alatoo.collabspace.dto.ChatDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatService {
    ChatDto create(ChatDto dto);
    ChatDto createPrivateChat(Long user1Id, Long user2Id);
    ChatDto getById(Long id);
    List<ChatDto> listAll();
    Page<ChatDto> listAllPaged(Pageable pageable);
    void delete(Long id);
}