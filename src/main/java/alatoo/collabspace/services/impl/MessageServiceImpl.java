package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.MessageDto;
import alatoo. collabspace.entities.Chat;
import alatoo.collabspace.entities.Message;
import alatoo.collabspace. entities.User;
import alatoo.collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.MessageMapper;
import alatoo.collabspace.repositories.ChatRepository;
import alatoo.collabspace.repositories.MessageRepository;
import alatoo.collabspace. repositories.UserRepository;
import alatoo.collabspace.services.MessageService;
import lombok. RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream. Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repo;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageMapper mapper;

    @Override
    @Transactional
    public MessageDto create(MessageDto dto) {
        Message entity = mapper. toEntity(dto);
        Chat chat = chatRepository.findById(dto.getChatId()).orElseThrow(() -> new NotFoundException("Chat not found"));
        User sender = userRepository.findById(dto.getSenderId()).orElseThrow(() -> new NotFoundException("User not found"));
        entity.setChat(chat);
        entity.setSender(sender);
        Message saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MessageDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("Message not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> listByChat(Long chatId) {
        return repo.findAll().stream()
                .filter(m -> m.getChat().getId().equals(chatId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MessageDto> listByChatPaged(Long chatId, Pageable pageable) {
        List<MessageDto> messages = repo.findAll().stream()
                .filter(m -> m.getChat().getId().equals(chatId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
        
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), messages.size());
        
        return new PageImpl<>(messages.subList(start, end), pageable, messages.size());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}