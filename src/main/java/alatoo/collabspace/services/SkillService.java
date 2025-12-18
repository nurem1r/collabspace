package alatoo.collabspace.services;

import alatoo.collabspace.dto. SkillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SkillService {
    SkillDto create(SkillDto dto);
    SkillDto getById(Long id);
    List<SkillDto> listAll();
    Page<SkillDto> listAllPaged(Pageable pageable);
    SkillDto update(Long id, SkillDto dto);
    void delete(Long id);
}