package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto. ProjectDto;
import alatoo.collabspace.dto.ProjectMemberDto;
import alatoo.collabspace.dto. ProjectApplicationDto;
import alatoo.collabspace.entities. Project;
import alatoo. collabspace.entities.enums.ProjectStatus;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.exception. NotFoundException;
import alatoo. collabspace.mappers.ProjectMapper;
import alatoo. collabspace.mappers.ProjectMemberMapper;
import alatoo.collabspace.mappers.ProjectApplicationMapper;
import alatoo. collabspace.repositories.ProjectRepository;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace.repositories.ProjectMemberRepository;
import alatoo.collabspace.repositories. ProjectApplicationRepository;
import alatoo.collabspace.services. ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain. Page;
import org.springframework. data.domain. Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation. Transactional;

import java. util.List;
import java. util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectApplicationRepository projectApplicationRepository;
    private final ProjectMapper projectMapper;
    private final ProjectMemberMapper projectMemberMapper;
    private final ProjectApplicationMapper projectApplicationMapper;

    @Override
    @Transactional
    public ProjectDto create(ProjectDto dto) {
        Project entity = projectMapper.toEntity(dto);
        if (dto.getOwnerId() != null) {
            User owner = userRepository.findById(dto.getOwnerId())
                    .orElseThrow(() -> new NotFoundException("Owner not found"));
            entity.setOwner(owner);
        } else {
            throw new RuntimeException("OwnerId required");
        }
        Project saved = projectRepository.save(entity);
        return projectMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDto getById(Long id) {
        return projectRepository.findById(id).map(projectMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Project not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> listAll() {
        return projectRepository.findAll().stream().map(projectMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectDto> listAllPaged(Pageable pageable) {
        return projectRepository.findAll(pageable).map(projectMapper::toDto);
    }

    @Override
    @Transactional
    public ProjectDto update(Long id, ProjectDto dto) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project not found"));
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
    @Transactional
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProjectDto completeProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project not found"));
        project.setStatus(ProjectStatus. DONE);
        Project updated = projectRepository.save(project);
        return projectMapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectMemberDto> getProjectMembers(Long id) {
        return projectMemberRepository. findAll().stream()
                .filter(pm -> pm.getProject().getId().equals(id))
                .map(projectMemberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectApplicationDto> getProjectApplications(Long id) {
        return projectApplicationRepository.findAll().stream()
                .filter(pa -> pa.getProject().getId().equals(id))
                .map(projectApplicationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> searchProjects(ProjectStatus status, String category, String keyword) {
        return projectRepository.findAll().stream()
                .filter(project -> {
                    boolean matches = true;
                    if (status != null) {
                        matches = project. getStatus() == status;
                    }
                    if (category != null && !category.isEmpty()) {
                        matches = matches && (project. getCategory() != null && project.getCategory().equalsIgnoreCase(category));
                    }
                    if (keyword != null && !keyword.isEmpty()) {
                        matches = matches && (
                                (project.getTitle() != null && project.getTitle().toLowerCase().contains(keyword.toLowerCase())) ||
                                (project.getDescription() != null && project.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                        );
                    }
                    return matches;
                })
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }
}