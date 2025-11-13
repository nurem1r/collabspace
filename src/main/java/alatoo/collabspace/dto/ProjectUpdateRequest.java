package alatoo.collabspace.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProjectUpdateRequest {
    private String title;
    private String description;
    private Set<String> tags;
    private Set<Long> requiredSkillIds;
    private String status;
}
