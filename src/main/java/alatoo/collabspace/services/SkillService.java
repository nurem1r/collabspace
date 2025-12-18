package alatoo.collabspace.services;

import alatoo.collabspace.dto.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto create(SkillDto dto);
    SkillDto getById(Long id);
    List<SkillDto> listAll();
    SkillDto update(Long id, SkillDto dto);
    void delete(Long id);
}