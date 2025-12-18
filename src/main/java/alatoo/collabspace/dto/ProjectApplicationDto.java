package alatoo.collabspace.dto;

import alatoo.collabspace.entities.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectApplicationDto {
    private Long id;
    private Long projectId;
    private Long userId;
    private String message;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
}