package alatoo.collabspace.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectRequest {
    @NotBlank
    private String title;

    private String description;

    private Set<String> tags;

    private Set<Long> requiredSkillIds;
}
