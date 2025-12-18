package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.*;
import alatoo.collabspace.entities.*;
import alatoo.collabspace.entities.enums.Role;
import alatoo.collabspace.exception. NotFoundException;
import alatoo. collabspace.mappers.*;
import alatoo.collabspace.repositories.*;
import alatoo.collabspace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util. List;
import java.util.Set;
import java.util.stream. Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserAchievementRepository userAchievementRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectApplicationRepository projectApplicationRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final UserMapper userMapper;
    private final SkillMapper skillMapper;
    private final UserAchievementMapper userAchievementMapper;
    private final ProjectMapper projectMapper;
    private final ProjectMemberMapper projectMemberMapper;
    private final ProjectApplicationMapper projectApplicationMapper;
    private final ChatMapper chatMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto create(UserDto dto, String rawPassword) {
        User user = userMapper.toEntity(dto);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.getRoles().add(Role.USER);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> listAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> listAllPaged(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setFaculty(dto.getFaculty());
        user.setStudyYear(dto.getStudyYear());
        user.setBio(dto.getBio());
        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Set<SkillDto> addSkill(Long userId, Long skillId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new NotFoundException("Skill not found"));
        user.getSkills().add(skill);
        userRepository.save(user);
        return user.getSkills().stream().map(skillMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Set<SkillDto> removeSkill(Long userId, Long skillId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        user.getSkills().removeIf(s -> s.getId().equals(skillId));
        userRepository.save(user);
        return user. getSkills().stream().map(skillMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<SkillDto> getUserSkills(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return user. getSkills().stream().map(skillMapper::toDto).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserAchievementDto> getUserAchievements(Long userId) {
        return userAchievementRepository.findAll().stream()
                .filter(ua -> ua.getUser().getId().equals(userId))
                .map(userAchievementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> getUserProjects(Long userId) {
        // Проекты где owner
        List<Project> ownedProjects = projectRepository.findAll().stream()
                .filter(p -> p.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
        
        // Проекты где member
        List<Project> memberProjects = projectMemberRepository.findAll().stream()
                .filter(pm -> pm.getUser().getId().equals(userId))
                .map(ProjectMember::getProject)
                .collect(Collectors.toList());
        
        // Объединяем и убираем дубликаты
        ownedProjects.addAll(memberProjects);
        return ownedProjects.stream()
                .distinct()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectMemberDto> getUserMemberships(Long userId) {
        return projectMemberRepository. findAll().stream()
                .filter(pm -> pm.getUser().getId().equals(userId))
                .map(projectMemberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectApplicationDto> getUserApplications(Long userId) {
        return projectApplicationRepository.findAll().stream()
                .filter(pa -> pa.getUser().getId().equals(userId))
                .map(projectApplicationMapper::toDto)
                .collect(Collectors. toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatDto> getUserChats(Long userId) {
        return chatMemberRepository.findAll().stream()
                .filter(cm -> cm.getUser().getId().equals(userId))
                .map(cm -> cm.getChat())
                .distinct()
                .map(chatMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> searchUsers(String skill, String faculty, Integer studyYear) {
        return userRepository.findAll().stream()
                .filter(user -> {
                    boolean matches = true;
                    if (skill != null && !skill.isEmpty()) {
                        matches = user.getSkills().stream()
                                .anyMatch(s -> s.getName().equalsIgnoreCase(skill));
                    }
                    if (faculty != null && !faculty.isEmpty()) {
                        matches = matches && (user.getFaculty() != null && user.getFaculty().equalsIgnoreCase(faculty));
                    }
                    if (studyYear != null) {
                        matches = matches && (user.getStudyYear() != null && user.getStudyYear().equals(studyYear));
                    }
                    return matches;
                })
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}