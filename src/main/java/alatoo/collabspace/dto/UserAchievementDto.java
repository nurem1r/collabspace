package alatoo.collabspace.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAchievementDto {
    private Long id;
    private Long userId;
    private Long achievementId;
    private LocalDateTime awardedAt;
    private Long verifiedBy;
}