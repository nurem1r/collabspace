package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.AchievementDto;
import alatoo.collabspace.entities.Achievement;
import alatoo.collabspace.entities.enums.AchievementSource;
import alatoo.collabspace.entities. Skill;
import alatoo. collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.AchievementMapper;
import alatoo.collabspace.repositories.AchievementRepository;
import alatoo.collabspace.repositories.SkillRepository;
import alatoo.collabspace.services.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework. data.domain.Page;
import org.springframework.data.domain. Pageable;
import org. springframework.stereotype.Service;
import org.springframework.transaction.annotation. Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final SkillRepository skillRepository;
    private final AchievementMapper achievementMapper;

    @Override
    @Transactional
    public AchievementDto create(AchievementDto dto) {
        Achievement entity = achievementMapper.toEntity(dto);
        if (dto.getSkillId() != null) {
            Skill skill = skillRepository.findById(dto.getSkillId())
                    .orElseThrow(() -> new NotFoundException("Skill not found"));
            entity. setSkill(skill);
        }
        Achievement saved = achievementRepository.save(entity);
        return achievementMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AchievementDto getById(Long id) {
        return achievementRepository.findById(id).map(achievementMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Achievement not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AchievementDto> listAll() {
        return achievementRepository.findAll().stream().map(achievementMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AchievementDto> listAllPaged(Pageable pageable) {
        return achievementRepository.findAll(pageable).map(achievementMapper::toDto);
    }

    @Override
    @Transactional
    public AchievementDto update(Long id, AchievementDto dto) {
        Achievement entity = achievementRepository.findById(id).orElseThrow(() -> new NotFoundException("Achievement not found"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setSource(dto.getSource());
        if (dto.getSkillId() != null) {
            Skill skill = skillRepository.findById(dto.getSkillId()).orElseThrow(() -> new NotFoundException("Skill not found"));
            entity.setSkill(skill);
        } else {
            entity.setSkill(null);
        }
        Achievement updated = achievementRepository. save(entity);
        return achievementMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        achievementRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AchievementDto> searchAchievements(AchievementSource source, Long skillId) {
        return achievementRepository.findAll().stream()
                .filter(achievement -> {
                    boolean matches = true;
                    if (source != null) {
                        matches = achievement.getSource() == source;
                    }
                    if (skillId != null) {
                        matches = matches && (achievement.getSkill() != null && achievement.getSkill().getId().equals(skillId));
                    }
                    return matches;
                })
                .map(achievementMapper::toDto)
                .collect(Collectors. toList());
    }
}