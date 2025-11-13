package alatoo.collabspace.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddSkillToUserRequest {
    @NotNull
    private Long skillId;
}
