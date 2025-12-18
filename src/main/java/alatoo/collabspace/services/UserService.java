package alatoo.collabspace.services;

import alatoo.collabspace.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto dto, String rawPassword);
    UserDto getById(Long id);
    List<UserDto> listAll();
    UserDto update(Long id, UserDto dto);
    void delete(Long id);
}