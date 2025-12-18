package alatoo.collabspace.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequiredRoleDto {
    private Long id;
    private Long projectId;
    private String roleName;
    private Integer slots;
}