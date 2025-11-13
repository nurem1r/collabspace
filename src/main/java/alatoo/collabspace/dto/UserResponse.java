package alatoo.collabspace.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String university;
    private String course;
    private int points;
    private Set<SkillDto> skills;
}
