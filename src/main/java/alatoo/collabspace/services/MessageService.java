package alatoo.collabspace.services;

import alatoo.collabspace.dto.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto create(MessageDto dto);
    MessageDto getById(Long id);
    List<MessageDto> listByChat(Long chatId);
    void delete(Long id);
}