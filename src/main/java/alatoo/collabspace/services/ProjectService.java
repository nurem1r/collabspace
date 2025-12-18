package alatoo.collabspace.services;

import alatoo.collabspace. dto.ProjectDto;
import alatoo.collabspace.dto.ProjectMemberDto;
import alatoo.collabspace. dto.ProjectApplicationDto;
import alatoo. collabspace.entities.enums.ProjectStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto dto);
    ProjectDto getById(Long id);
    List<ProjectDto> listAll();
    Page<ProjectDto> listAllPaged(Pageable pageable);
    ProjectDto update(Long id, ProjectDto dto);
    void delete(Long id);
    
    // Бизнес-логика
    ProjectDto completeProject(Long id);
    List<ProjectMemberDto> getProjectMembers(Long id);
    List<ProjectApplicationDto> getProjectApplications(Long id);
    
    // Поиск
    List<ProjectDto> searchProjects(ProjectStatus status, String category, String keyword);
}