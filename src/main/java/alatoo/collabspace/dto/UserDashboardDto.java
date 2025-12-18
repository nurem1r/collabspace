package alatoo.collabspace.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDashboardDto {
    private Long userId;
    private Long ownedProjectsCount;
    private Long memberProjectsCount;
    private Long achievementsCount;
    private Long pendingApplicationsCount;
}