package alatoo.collabspace.dto;

import alatoo.collabspace.entities.enums.AchievementSource;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementDto {
    private Long id;
    private String name;
    private String description;
    private Long skillId;
    private AchievementSource source;
    private LocalDateTime createdAt;
}