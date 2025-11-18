package alatoo.collabspace.services;

import alatoo.collabspace.dto.SkillCreateRequest;
import alatoo.collabspace.entities.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    Skill createSkill(SkillCreateRequest request);
    Optional<Skill> getSkillById(Long id);
    Optional<Skill> getSkillByName(String name);
    List<Skill> getAllSkills();
    Skill updateSkill(Long id, SkillCreateRequest request);
    void deleteSkill(Long id);

    boolean existsByName(String name);
    List<Skill> searchByNameFragment(String fragment);
}
