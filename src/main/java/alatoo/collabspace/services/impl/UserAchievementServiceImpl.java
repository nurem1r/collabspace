package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.UserAchievementDto;
import alatoo.collabspace.entities.Achievement;
import alatoo.collabspace.entities.User;
import alatoo.collabspace.entities.UserAchievement;
import alatoo. collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.UserAchievementMapper;
import alatoo.collabspace.repositories.AchievementRepository;
import alatoo.collabspace.repositories.UserAchievementRepository;
import alatoo.collabspace.repositories.UserRepository;
import alatoo.collabspace.services. UserAchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework. data.domain.Page;
import org.springframework.data.domain. Pageable;
import org. springframework.stereotype.Service;
import org.springframework.transaction.annotation. Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAchievementServiceImpl implements UserAchievementService {

    private final UserAchievementRepository userAchievementRepository;
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;
    private final UserAchievementMapper mapper;

    @Override
    @Transactional
    public UserAchievementDto assign(UserAchievementDto dto) {
        UserAchievement entity = mapper.toEntity(dto);
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Achievement achievement = achievementRepository.findById(dto.getAchievementId()).orElseThrow(() -> new NotFoundException("Achievement not found"));
        entity.setUser(user);
        entity.setAchievement(achievement);
        UserAchievement saved = userAchievementRepository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAchievementDto getById(Long id) {
        return userAchievementRepository. findById(id).map(mapper::toDto).orElseThrow(() -> new NotFoundException("UserAchievement not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserAchievementDto> listAll() {
        return userAchievementRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserAchievementDto> listAllPaged(Pageable pageable) {
        return userAchievementRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public UserAchievementDto verify(Long id, Long verifierId) {
        UserAchievement ua = userAchievementRepository. findById(id).orElseThrow(() -> new NotFoundException("UserAchievement not found"));
        ua.setVerifiedBy(verifierId);
        UserAchievement updated = userAchievementRepository.save(ua);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userAchievementRepository.deleteById(id);
    }
}