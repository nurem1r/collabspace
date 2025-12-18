package alatoo.collabspace.services;

import alatoo.collabspace.dto.UserAchievementDto;

import java.util.List;

public interface UserAchievementService {
    UserAchievementDto assign(UserAchievementDto dto);
    UserAchievementDto getById(Long id);
    List<UserAchievementDto> listAll();
    void delete(Long id);
}