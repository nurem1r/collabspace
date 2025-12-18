package alatoo.collabspace.services;

import alatoo. collabspace.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    MessageDto create(MessageDto dto);
    MessageDto getById(Long id);
    List<MessageDto> listByChat(Long chatId);
    Page<MessageDto> listByChatPaged(Long chatId, Pageable pageable);
    void delete(Long id);
}