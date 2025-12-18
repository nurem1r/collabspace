package alatoo.collabspace.services;

import alatoo.collabspace.dto.ProjectMemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectMemberService {
    ProjectMemberDto add(ProjectMemberDto dto);
    ProjectMemberDto getById(Long id);
    List<ProjectMemberDto> listByProject(Long projectId);
    Page<ProjectMemberDto> listAllPaged(Pageable pageable);
    ProjectMemberDto rateMember(Long id, Integer rating, String feedback);
    void remove(Long id);
}