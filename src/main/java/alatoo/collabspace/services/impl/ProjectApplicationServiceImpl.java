package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.ProjectApplicationDto;
import alatoo.collabspace.entities.*;
import alatoo.collabspace.entities.enums.ApplicationStatus;
import alatoo.collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.ProjectApplicationMapper;
import alatoo.collabspace.repositories.ProjectApplicationRepository;
import alatoo.collabspace.repositories.ProjectRepository;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace.repositories.ProjectMemberRepository;
import alatoo.collabspace.services.ProjectApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data. domain.Page;
import org. springframework.data.domain.Pageable;
import org.springframework. stereotype.Service;
import org. springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectApplicationServiceImpl implements ProjectApplicationService {

    private final ProjectApplicationRepository repo;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectApplicationMapper mapper;

    @Override
    @Transactional
    public ProjectApplicationDto apply(ProjectApplicationDto dto) {
        ProjectApplication entity = mapper.toEntity(dto);
        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow(() -> new NotFoundException("Project not found"));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        entity.setProject(project);
        entity.setUser(user);
        entity.setStatus(ApplicationStatus. PENDING);
        ProjectApplication saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectApplicationDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("ProjectApplication not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectApplicationDto> listByProject(Long projectId) {
        return repo.findAll().stream()
                .filter(pa -> pa.getProject().getId().equals(projectId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectApplicationDto> listByStatus(ApplicationStatus status) {
        return repo.findAll().stream()
                .filter(pa -> pa. getStatus() == status)
                .map(mapper::toDto)
                .collect(Collectors. toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectApplicationDto> listAllPaged(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public ProjectApplicationDto processApplication(Long id, String action) {
        ProjectApplication app = repo.findById(id).orElseThrow(() -> new NotFoundException("ProjectApplication not found"));
        
        if ("ACCEPT".equalsIgnoreCase(action)) {
            app.setStatus(ApplicationStatus.ACCEPTED);
            
            // Автоматически создаём ProjectMember
            ProjectMember member = ProjectMember.builder()
                    .project(app.getProject())
                    .user(app.getUser())
                    . roleInProject("Member") // дефолтная роль, можно передавать в запросе
                    .build();
            projectMemberRepository.save(member);
            
        } else if ("REJECT".equalsIgnoreCase(action)) {
            app.setStatus(ApplicationStatus.REJECTED);
        } else {
            throw new IllegalArgumentException("Unsupported action");
        }
        
        ProjectApplication updated = repo.save(app);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}