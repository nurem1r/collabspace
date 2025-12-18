package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto. ProjectMemberDto;
import alatoo.collabspace.entities.Project;
import alatoo.collabspace.entities.ProjectMember;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.exception. NotFoundException;
import alatoo. collabspace.mappers.ProjectMemberMapper;
import alatoo.collabspace.repositories. ProjectMemberRepository;
import alatoo.collabspace.repositories.ProjectRepository;
import alatoo. collabspace.repositories.UserRepository;
import alatoo.collabspace.services.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain. Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation. Transactional;

import java. util.List;
import java. util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberMapper mapper;

    @Override
    @Transactional
    public ProjectMemberDto add(ProjectMemberDto dto) {
        ProjectMember entity = mapper.toEntity(dto);
        Project project = projectRepository.findById(dto.getProjectId()).orElseThrow(() -> new NotFoundException("Project not found"));
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        entity.setProject(project);
        entity.setUser(user);
        ProjectMember saved = projectMemberRepository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectMemberDto getById(Long id) {
        return projectMemberRepository.findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("ProjectMember not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectMemberDto> listByProject(Long projectId) {
        return projectMemberRepository. findAll().stream()
                .filter(pm -> pm.getProject().getId().equals(projectId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectMemberDto> listAllPaged(Pageable pageable) {
        return projectMemberRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public ProjectMemberDto rateMember(Long id, Integer rating, String feedback) {
        ProjectMember pm = projectMemberRepository.findById(id).orElseThrow(() -> new NotFoundException("ProjectMember not found"));
        pm.setPerformanceRating(rating);
        pm.setFeedback(feedback);
        ProjectMember updated = projectMemberRepository.save(pm);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        projectMemberRepository.deleteById(id);
    }
}