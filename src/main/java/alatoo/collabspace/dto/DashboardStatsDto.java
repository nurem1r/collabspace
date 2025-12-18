package alatoo.collabspace.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDto {
    private Long totalUsers;
    private Long totalProjects;
    private Long totalSkills;
    private Long totalAchievements;
    private Long activeProjects;
}