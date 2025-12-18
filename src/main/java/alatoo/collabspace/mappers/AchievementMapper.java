package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.AchievementDto;
import alatoo.collabspace.entities.Achievement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AchievementMapper {
    @Mapping(source = "skill.id", target = "skillId")
    AchievementDto toDto(Achievement entity);

    @Mapping(source = "skillId", target = "skill.id")
    @Mapping(target = "createdAt", ignore = true)
    Achievement toEntity(AchievementDto dto);
}