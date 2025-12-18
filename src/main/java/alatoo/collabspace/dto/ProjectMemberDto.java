package alatoo.collabspace.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMemberDto {
    private Long id;
    private Long projectId;
    private Long userId;
    private String roleInProject;
    private LocalDateTime joinedAt;
    private Integer performanceRating;
    private String feedback;
}