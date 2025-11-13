package alatoo.collabspace.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillCreateRequest {
    @NotBlank
    private String name;
}
