package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.ProjectRequiredRoleDto;
import alatoo.collabspace.entities.Project;
import alatoo.collabspace.entities.ProjectRequiredRole;
import alatoo.collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.ProjectRequiredRoleMapper;
import alatoo.collabspace.repositories.ProjectRepository;
import alatoo.collabspace.repositories.ProjectRequiredRoleRepository;
import alatoo.collabspace.services.ProjectRequiredRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectRequiredRoleServiceImpl implements ProjectRequiredRoleService {

    private final ProjectRequiredRoleRepository repo;
    private final ProjectRepository projectRepository;
    private final ProjectRequiredRoleMapper mapper;

    @Override
    @Transactional
    public ProjectRequiredRoleDto create(ProjectRequiredRoleDto dto) {
        ProjectRequiredRole entity = mapper.toEntity(dto);
        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow(() -> new NotFoundException("Project not found"));
        entity.setProject(project);
        ProjectRequiredRole saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectRequiredRoleDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("ProjectRequiredRole not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectRequiredRoleDto> listByProject(Long projectId) {
        return repo.findAll().stream()
                .filter(pr -> pr.getProject().getId().equals(projectId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProjectRequiredRoleDto update(Long id, ProjectRequiredRoleDto dto) {
        ProjectRequiredRole entity = repo.findById(id).orElseThrow(() -> new NotFoundException("ProjectRequiredRole not found"));
        entity.setRoleName(dto.getRoleName());
        entity.setSlots(dto.getSlots());
        ProjectRequiredRole updated = repo.save(entity);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}