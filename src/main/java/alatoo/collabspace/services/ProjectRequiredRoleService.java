package alatoo.collabspace.services;

import alatoo.collabspace.dto.ProjectRequiredRoleDto;

import java.util.List;

public interface ProjectRequiredRoleService {
    ProjectRequiredRoleDto create(ProjectRequiredRoleDto dto);
    ProjectRequiredRoleDto getById(Long id);
    List<ProjectRequiredRoleDto> listByProject(Long projectId);
    ProjectRequiredRoleDto update(Long id, ProjectRequiredRoleDto dto);
    void delete(Long id);
}