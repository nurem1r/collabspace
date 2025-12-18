package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.SkillDto;
import alatoo.collabspace.entities.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillDto toDto(Skill entity);
    Skill toEntity(SkillDto dto);
}