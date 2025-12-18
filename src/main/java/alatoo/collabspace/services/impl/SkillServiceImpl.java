package alatoo.collabspace.services.impl;

import alatoo.collabspace.dto.SkillDto;
import alatoo.collabspace.entities.Skill;
import alatoo.collabspace.exception.NotFoundException;
import alatoo.collabspace.mappers.SkillMapper;
import alatoo.collabspace.repositories.SkillRepository;
import alatoo.collabspace.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    @Transactional
    public SkillDto create(SkillDto dto) {
        Skill skill = skillMapper.toEntity(dto);
        Skill saved = skillRepository.save(skill);
        return skillMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SkillDto getById(Long id) {
        return skillRepository.findById(id).map(skillMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Skill not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SkillDto> listAll() {
        return skillRepository.findAll().stream().map(skillMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SkillDto update(Long id, SkillDto dto) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new NotFoundException("Skill not found"));
        skill.setName(dto.getName());
        Skill updated = skillRepository.save(skill);
        return skillMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }
}