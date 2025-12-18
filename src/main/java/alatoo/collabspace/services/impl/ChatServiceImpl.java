package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto. ChatDto;
import alatoo.collabspace.entities.*;
import alatoo.collabspace.entities.enums.ChatType;
import alatoo.collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.ChatMapper;
import alatoo.collabspace.repositories.ChatRepository;
import alatoo.collabspace. repositories.ProjectRepository;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace.repositories.ChatMemberRepository;
import alatoo.collabspace.services.ChatService;
import lombok. RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream. Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repo;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final ChatMapper mapper;

    @Override
    @Transactional
    public ChatDto create(ChatDto dto) {
        Chat entity = mapper.toEntity(dto);
        if (dto.getProjectId() != null) {
            Project project = projectRepository.findById(dto. getProjectId()).orElseThrow(() -> new NotFoundException("Project not found"));
            entity.setProject(project);
        }
        Chat saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ChatDto createPrivateChat(Long user1Id, Long user2Id) {
        User user1 = userRepository.findById(user1Id).orElseThrow(() -> new NotFoundException("User1 not found"));
        User user2 = userRepository.findById(user2Id).orElseThrow(() -> new NotFoundException("User2 not found"));
        
        // Проверка на существующий приватный чат между этими пользователями
        Optional<Chat> existingChat = repo.findAll().stream()
                .filter(chat -> chat.getType() == ChatType.PRIVATE && chat.getProject() == null)
                .filter(chat -> {
                    List<Long> memberIds = chat.getMembers().stream()
                            .map(cm -> cm.getUser().getId())
                            . collect(Collectors.toList());
                    return memberIds.contains(user1Id) && memberIds.contains(user2Id) && memberIds.size() == 2;
                })
                .findFirst();
        
        if (existingChat. isPresent()) {
            return mapper.toDto(existingChat.get());
        }
        
        // Создаём новый приватный чат
        Chat chat = Chat.builder()
                .type(ChatType.PRIVATE)
                .build();
        Chat saved = repo.save(chat);
        
        // Добавляем участников
        ChatMember cm1 = ChatMember.builder().chat(saved).user(user1).build();
        ChatMember cm2 = ChatMember.builder().chat(saved).user(user2).build();
        chatMemberRepository.save(cm1);
        chatMemberRepository.save(cm2);
        
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ChatDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("Chat not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatDto> listAll() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChatDto> listAllPaged(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}