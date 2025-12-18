package alatoo.collabspace.dto;

import alatoo.collabspace.entities.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String faculty;
    private Integer studyYear;
    private String bio;
    private Set<Role> roles;
    private LocalDateTime createdAt;
}