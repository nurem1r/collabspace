package alatoo.collabspace.services;

import alatoo.collabspace.dto.ChatMemberDto;

import java.util.List;

public interface ChatMemberService {
    ChatMemberDto add(ChatMemberDto dto);
    ChatMemberDto getById(Long id);
    List<ChatMemberDto> listByChat(Long chatId);
    void remove(Long id);
}