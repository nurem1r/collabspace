package alatoo.collabspace.services;

import alatoo.collabspace.entities.Project;
import alatoo.collabspace.entities.Skill;
import alatoo.collabspace.entities.enums.Status;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProjectService {

    // CRUD
    Project createProject(Project project, Long leaderId, Set<Long> requiredSkillIds);
    Optional<Project> getProjectById(Long id);
    List<Project> getAllProjects();
    Project updateProject(Long id, Project updated);
    void deleteProject(Long id);

    // Queries
    List<Project> getProjectsByLeader(Long leaderId);
    List<Project> getProjectsByMember(Long userId);
    List<Project> getProjectsByStatus(Status status);
    List<Project> findByTag(String tag);
    List<Project> findByRequiredSkill(Long skillId);

    // Members
    Project addMember(Long projectId, Long userId);
    Project removeMember(Long projectId, Long userId);

    // Required skills
    Project addRequiredSkill(Long projectId, Long skillId);
    Project removeRequiredSkill(Long projectId, Long skillId);
    Set<Skill> getRequiredSkills(Long projectId);

    // Status
    Project changeStatus(Long projectId, Status status);

    boolean isUserMember(Long projectId, Long userId);
}
