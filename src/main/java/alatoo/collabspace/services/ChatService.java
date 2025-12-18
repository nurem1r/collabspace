package alatoo.collabspace.services;

import alatoo.collabspace.dto.ChatDto;

import java.util.List;

public interface ChatService {
    ChatDto create(ChatDto dto);
    ChatDto getById(Long id);
    List<ChatDto> listAll();
    void delete(Long id);
}