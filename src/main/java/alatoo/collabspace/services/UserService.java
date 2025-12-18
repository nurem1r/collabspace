package alatoo.collabspace.services;

import alatoo.collabspace. dto.UserDto;
import alatoo.collabspace.dto. SkillDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto create(UserDto dto, String rawPassword);
    UserDto getById(Long id);
    List<UserDto> listAll();
    UserDto update(Long id, UserDto dto);
    void delete(Long id);


    Set<SkillDto> addSkill(Long userId, Long skillId);
    Set<SkillDto> removeSkill(Long userId, Long skillId);
    Set<SkillDto> getUserSkills(Long userId);
}