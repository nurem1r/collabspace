package alatoo.collabspace.services;

import alatoo.collabspace.dto.ProjectMemberDto;

import java.util.List;

public interface ProjectMemberService {
    ProjectMemberDto add(ProjectMemberDto dto);
    ProjectMemberDto getById(Long id);
    List<ProjectMemberDto> listByProject(Long projectId);
    ProjectMemberDto rateMember(Long id, Integer rating, String feedback);
    void remove(Long id);
}