package alatoo.collabspace.services;

import alatoo.collabspace.dto.AchievementDto;
import alatoo.collabspace.entities.enums.AchievementSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AchievementService {
    AchievementDto create(AchievementDto dto);
    AchievementDto getById(Long id);
    List<AchievementDto> listAll();
    Page<AchievementDto> listAllPaged(Pageable pageable);
    AchievementDto update(Long id, AchievementDto dto);
    void delete(Long id);
    
    // Поиск
    List<AchievementDto> searchAchievements(AchievementSource source, Long skillId);
}