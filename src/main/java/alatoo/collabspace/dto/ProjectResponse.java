package alatoo.collabspace.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private Set<String> tags;
    private String status;
    private Long leaderId;
    private String leaderName;
    private Set<UserSummaryDto> members;
    private Set<SkillDto> requiredSkills;
    private LocalDateTime createdAt;
}
