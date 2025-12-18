package alatoo.collabspace.services;

import alatoo.collabspace. dto.ProjectRequiredRoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain. Pageable;

import java.util.List;

public interface ProjectRequiredRoleService {
    ProjectRequiredRoleDto create(ProjectRequiredRoleDto dto);
    ProjectRequiredRoleDto getById(Long id);
    List<ProjectRequiredRoleDto> listByProject(Long projectId);
    Page<ProjectRequiredRoleDto> listAllPaged(Pageable pageable);
    ProjectRequiredRoleDto update(Long id, ProjectRequiredRoleDto dto);
    void delete(Long id);
}