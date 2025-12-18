package alatoo.collabspace.services.impl;

import alatoo.collabspace. dto.DashboardStatsDto;
import alatoo.collabspace.dto.UserDashboardDto;
import alatoo.collabspace.entities.ApplicationStatus;
import alatoo. collabspace.entities.ProjectStatus;
import alatoo. collabspace.exception.NotFoundException;
import alatoo.collabspace.repositories.*;
import alatoo.collabspace.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org. springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;
    private final AchievementRepository achievementRepository;
    private final UserAchievementRepository userAchievementRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectApplicationRepository projectApplicationRepository;

    @Override
    @Transactional(readOnly = true)
    public DashboardStatsDto getGlobalStats() {
        long totalUsers = userRepository.count();
        long totalProjects = projectRepository.count();
        long totalSkills = skillRepository.count();
        long totalAchievements = achievementRepository.count();
        long activeProjects = projectRepository.findAll().stream()
                .filter(p -> p.getStatus() == ProjectStatus.IN_PROGRESS)
                .count();

        return DashboardStatsDto.builder()
                .totalUsers(totalUsers)
                .totalProjects(totalProjects)
                .totalSkills(totalSkills)
                .totalAchievements(totalAchievements)
                .activeProjects(activeProjects)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDashboardDto getUserDashboard(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }

        long ownedProjects = projectRepository.findAll().stream()
                .filter(p -> p.getOwner().getId().equals(userId))
                .count();

        long memberProjects = projectMemberRepository.findAll().stream()
                .filter(pm -> pm.getUser().getId().equals(userId))
                .count();

        long achievements = userAchievementRepository.findAll().stream()
                .filter(ua -> ua.getUser().getId().equals(userId))
                .count();

        long pendingApplications = projectApplicationRepository.findAll().stream()
                .filter(pa -> pa.getUser().getId().equals(userId) && pa.getStatus() == ApplicationStatus.PENDING)
                .count();

        return UserDashboardDto.builder()
                .userId(userId)
                .ownedProjectsCount(ownedProjects)
                .memberProjectsCount(memberProjects)
                .achievementsCount(achievements)
                .pendingApplicationsCount(pendingApplications)
                .build();
    }
}