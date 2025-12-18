package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.ProjectDto;
import alatoo.collabspace.entities.Project;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.mappers.ProjectMapper;
import alatoo.collabspace.repositories.ProjectRepository;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto create(ProjectDto dto) {
        Project entity = projectMapper.toEntity(dto);
        if (dto.getOwnerId() != null) {
            User owner = userRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            entity.setOwner(owner);
        } else {
            throw new RuntimeException("OwnerId required");
        }
        Project saved = projectRepository.save(entity);
        return projectMapper.toDto(saved);
    }

    @Override
    public ProjectDto getById(Long id) {
        return projectRepository.findById(id).map(projectMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<ProjectDto> listAll() {
        return projectRepository.findAll().stream().map(projectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProjectDto update(Long id, ProjectDto dto) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setTitle(dto.getTitle());
        project.setShortDescription(dto.getShortDescription());
        project.setDescription(dto.getDescription());
        project.setCategory(dto.getCategory());
        project.setStatus(dto.getStatus());
        project.setMaxMembers(dto.getMaxMembers());
        Project updated = projectRepository.save(project);
        return projectMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}