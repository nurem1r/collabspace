package alatoo.collabspace.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String university;
    private String course;
}
