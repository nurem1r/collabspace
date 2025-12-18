package alatoo.collabspace.dto;

import alatoo.collabspace.entities.enums.ProjectStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long id;
    private Long ownerId;
    private String title;
    private String shortDescription;
    private String description;
    private String category;
    private ProjectStatus status;
    private Integer maxMembers;
    private LocalDateTime createdAt;
}