package alatoo.collabspace.services;

import alatoo.collabspace.dto.UserAchievementDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserAchievementService {
    UserAchievementDto assign(UserAchievementDto dto);
    UserAchievementDto getById(Long id);
    List<UserAchievementDto> listAll();
    Page<UserAchievementDto> listAllPaged(Pageable pageable);
    UserAchievementDto verify(Long id, Long verifierId);
    void delete(Long id);
}