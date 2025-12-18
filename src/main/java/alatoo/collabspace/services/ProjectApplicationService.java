package alatoo.collabspace.services;

import alatoo.collabspace.dto.ProjectApplicationDto;
import alatoo.collabspace.entities.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectApplicationService {
    ProjectApplicationDto apply(ProjectApplicationDto dto);
    ProjectApplicationDto getById(Long id);
    List<ProjectApplicationDto> listByProject(Long projectId);
    List<ProjectApplicationDto> listByStatus(ApplicationStatus status);
    Page<ProjectApplicationDto> listAllPaged(Pageable pageable);
    ProjectApplicationDto processApplication(Long id, String action);
    void delete(Long id);
}