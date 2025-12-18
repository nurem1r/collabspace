package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.ChatDto;
import alatoo.collabspace.entities.Project;
import alatoo.collabspace.entities.Chat;
import alatoo.collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.ChatMapper;
import alatoo.collabspace.repositories.ChatRepository;
import alatoo.collabspace.repositories.ProjectRepository;
import alatoo.collabspace.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository repo;
    private final ProjectRepository projectRepository;
    private final ChatMapper mapper;

    @Override
    @Transactional
    public ChatDto create(ChatDto dto) {
        Chat entity = mapper.toEntity(dto);
        if (dto.getProjectId() != null) {
            Project project = projectRepository.findById(dto.getProjectId()).orElseThrow(() -> new NotFoundException("Project not found"));
            entity.setProject(project);
        }
        Chat saved = repo.save(entity);
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
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}