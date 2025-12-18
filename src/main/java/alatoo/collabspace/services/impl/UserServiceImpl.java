package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto. UserDto;
import alatoo.collabspace.dto. SkillDto;
import alatoo.collabspace.entities. User;
import alatoo. collabspace.entities. Skill;
import alatoo. collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.UserMapper;
import alatoo.collabspace.mappers.SkillMapper;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace. repositories.SkillRepository;
import alatoo.collabspace.services.UserService;
import lombok. RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream. Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserMapper userMapper;
    private final SkillMapper skillMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto create(UserDto dto, String rawPassword) {
        User user = userMapper.toEntity(dto);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.getRoles().add(alatoo.collabspace.entities.enums.Role.USER);
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
        return userRepository. findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
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
}