package alatoo.collabspace.services;

import alatoo.collabspace.dto.ProjectApplicationDto;

import java.util.List;

public interface ProjectApplicationService {
    ProjectApplicationDto apply(ProjectApplicationDto dto);
    ProjectApplicationDto getById(Long id);
    List<ProjectApplicationDto> listByProject(Long projectId);
    ProjectApplicationDto processApplication(Long id, String action); // action = ACCEPT/REJECT
    void delete(Long id);
}