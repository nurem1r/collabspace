package alatoo.collabspace.services;

import alatoo.collabspace.dto.AchievementDto;

import java.util.List;

public interface AchievementService {
    AchievementDto create(AchievementDto dto);
    AchievementDto getById(Long id);
    List<AchievementDto> listAll();
    AchievementDto update(Long id, AchievementDto dto);
    void delete(Long id);
}