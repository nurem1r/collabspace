package alatoo.collabspace.services;

import alatoo.collabspace.dto.ChatMemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatMemberService {
    ChatMemberDto add(ChatMemberDto dto);
    ChatMemberDto getById(Long id);
    List<ChatMemberDto> listByChat(Long chatId);
    Page<ChatMemberDto> listAllPaged(Pageable pageable);
    void remove(Long id);
}