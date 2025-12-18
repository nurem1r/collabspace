package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.UserDto;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.mappers.UserMapper;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDto dto, String rawPassword) {
        User user = userMapper.toEntity(dto);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.getRoles().add(alatoo.collabspace.entities.enums.Role.USER);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public UserDto getById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserDto> listAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setFaculty(dto.getFaculty());
        user.setStudyYear(dto.getStudyYear());
        user.setBio(dto.getBio());
        User updated = userRepository.save(user);
        return userMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}