package alatoo.collabspace.config;

import alatoo.collabspace.repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component("projectSecurity")
@RequiredArgsConstructor
public class ProjectSecurity {

    private final ProjectRepository projectRepository;

    public boolean isProjectOwner(Long projectId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return false;
        }
        String userEmail = auth.getName();
        return projectRepository.existsByOwnerEmailAndId(userEmail, projectId);
    }

    public boolean isProjectMember(Long projectId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return false;
        }
        String userEmail = auth.getName();
        return projectRepository.existsByProjectMemberEmailAndId(userEmail, projectId);
    }

    // Универсальный метод с разными уровнями доступа
    public boolean checkProjectAccess(Long projectId, String accessLevel) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        switch (accessLevel.toUpperCase()) {
            case "OWNER":
                return projectRepository.existsByOwnerEmailAndId(userEmail, projectId);
            case "MEMBER":
                return projectRepository.existsByProjectMemberEmailAndId(userEmail, projectId);
            case "ADMIN":
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                return auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            default:
                return false;
        }
    }
}