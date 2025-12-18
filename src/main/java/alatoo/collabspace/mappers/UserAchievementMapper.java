package alatoo.collabspace.mappers;

import alatoo.collabspace.dto.UserAchievementDto;
import alatoo.collabspace.entities.UserAchievement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAchievementMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "achievement.id", target = "achievementId")
    UserAchievementDto toDto(UserAchievement entity);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "achievementId", target = "achievement.id")
    @Mapping(target = "awardedAt", ignore = true)
    UserAchievement toEntity(UserAchievementDto dto);
}