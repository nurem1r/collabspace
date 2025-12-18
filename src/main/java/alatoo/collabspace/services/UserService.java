package alatoo.collabspace.services;

import alatoo.collabspace.dto.UserDto;
import alatoo. collabspace.dto. SkillDto;
import alatoo.collabspace.dto.UserAchievementDto;
import alatoo.collabspace.dto.ProjectDto;
import alatoo. collabspace.dto.ChatDto;
import alatoo. collabspace.dto.ProjectMemberDto;
import alatoo.collabspace.dto. ProjectApplicationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain. Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto create(UserDto dto, String rawPassword);
    UserDto getById(Long id);
    List<UserDto> listAll();
    Page<UserDto> listAllPaged(Pageable pageable);
    UserDto update(Long id, UserDto dto);
    void delete(Long id);
    
    // Управление навыками пользователя
    Set<SkillDto> addSkill(Long userId, Long skillId);
    Set<SkillDto> removeSkill(Long userId, Long skillId);
    Set<SkillDto> getUserSkills(Long userId);
    
    // Получение связанных данных
    List<UserAchievementDto> getUserAchievements(Long userId);
    List<ProjectDto> getUserProjects(Long userId);
    List<ProjectMemberDto> getUserMemberships(Long userId);
    List<ProjectApplicationDto> getUserApplications(Long userId);
    List<ChatDto> getUserChats(Long userId);
    
    // Поиск
    List<UserDto> searchUsers(String skill, String faculty, Integer studyYear);
}