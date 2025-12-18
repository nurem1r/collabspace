package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto. ChatMemberDto;
import alatoo.collabspace.entities.Chat;
import alatoo.collabspace.entities.ChatMember;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.exception. NotFoundException;
import alatoo. collabspace.mappers.ChatMemberMapper;
import alatoo.collabspace.repositories.ChatMemberRepository;
import alatoo.collabspace.repositories.ChatRepository;
import alatoo. collabspace.repositories.UserRepository;
import alatoo.collabspace.services.ChatMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream. Collectors;

@Service
@RequiredArgsConstructor
public class ChatMemberServiceImpl implements ChatMemberService {

    private final ChatMemberRepository repo;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMemberMapper mapper;

    @Override
    @Transactional
    public ChatMemberDto add(ChatMemberDto dto) {
        ChatMember entity = mapper.toEntity(dto);
        Chat chat = chatRepository.findById(dto. getChatId()).orElseThrow(() -> new NotFoundException("Chat not found"));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        entity.setChat(chat);
        entity.setUser(user);
        ChatMember saved = repo.save(entity);
        return mapper. toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ChatMemberDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("ChatMember not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMemberDto> listByChat(Long chatId) {
        return repo.findAll().stream()
                .filter(cm -> cm.getChat().getId().equals(chatId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChatMemberDto> listAllPaged(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repo.deleteById(id);
    }
}