package alatoo.collabspace.services;

import alatoo.collabspace.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto dto);
    ProjectDto getById(Long id);
    List<ProjectDto> listAll();
    ProjectDto update(Long id, ProjectDto dto);
    void delete(Long id);
}